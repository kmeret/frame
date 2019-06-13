package io.github.kmeret.frame.network

import android.widget.ImageView
import com.bumptech.glide.Glide
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun ImageView.loadByUrl(url: String) = Glide.with(this.context).load(url).into(this)

fun <T> Call<T>.request(onSuccess: (response: T) -> Unit) = enqueue(object : Callback<T> {

    override fun onResponse(call: Call<T>?, response: Response<T>) {

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

        val body = response.body() ?: throw ApiException

        onSuccess.invoke(body)
    }

    override fun onFailure(call: Call<T>?, t: Throwable) {
        handleServerError(999)
    }

    private fun handleServerError(responseCode: Int) {
        throw when(responseCode) {
            401 -> NotAuthException
            999 -> NoConnectionException
            else -> UnknownResponseCodeException
        }
    }

})
