package io.github.kmeret.frame.domain.cases

import io.github.kmeret.frame.lifecycle.LiveUseCase
import io.github.kmeret.frame.network.ApiMessageException
import io.github.kmeret.frame.network.NoConnectionException
import io.github.kmeret.frame.network.NotAuthException
import io.github.kmeret.frame.network.UnknownResponseCodeException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback

class NetworkLiveUseCase<Response> : LiveUseCase<Call<Response>, Response>() {

    override fun execute(request: Call<Response>) {

        loading.value = true

        request.enqueue(object : Callback<Response> {

            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {

                loading.value = false

                if (!response.isSuccessful) {
                    val errorBody = response.errorBody()
                    if (errorBody == null) {
                        handleServerError(response.code())
                        return
                    }
                    try {
                        throw ApiMessageException(JSONObject(errorBody.string()).getString("message"))
                    } catch (ex: Exception) {
                        handleServerError(response.code())
                    }
                }

                val body = response.body()

                if (body == null) {
                    empty.value = true
                    return
                }

                if (body is List<*> && body.isEmpty()) {
                    empty.value = true
                    return
                }

                empty.value = false
                data.value = body
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                loading.value = false
                handleServerError(999)
            }
        })

    }

    private fun handleServerError(responseCode: Int) {
        error.value = when(responseCode) {
            401 -> NotAuthException
            999 -> NoConnectionException
            else -> UnknownResponseCodeException
        }
    }

}
