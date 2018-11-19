package io.github.kmeret.frame.demo.domain.cases

import io.github.kmeret.frame.lifecycle.LiveUseCase
import io.github.kmeret.frame.network.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import kotlin.Exception

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
                        throw ApiException(JSONObject(errorBody.string()).getString("message"))
                    } catch (ex: Exception) {
                        handleServerError(response.code())
                    }
                }

                val body = response.body()

                if (body == null) {
                    empty.invoke()
                    return
                }

                if (body is List<*> && body.isEmpty()) {
                    empty.invoke()
                    return
                }

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
