package io.github.kmeret.frame.network

import android.widget.ImageView
import com.squareup.picasso.Picasso
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> Call<T>.request(onSuccess: (response: T) -> Unit,
                        onError: (ex: ApiException) -> Unit) = enqueue(object : Callback<T> {

    override fun onResponse(call: Call<T>?, response: Response<T>) {
        if (!response.isSuccessful) {
            val errorBody = response.errorBody()?.string() ?: return
            try {
                onError.invoke(ApiException(JSONObject(errorBody).getString("message")))
                return
            } catch (ex: Exception) {
                onError.invoke(ApiException())
            }
        }

        val body = response.body()
        if (body == null) {
            onError.invoke(ApiException("Null response body!"))
            return
        }

        onSuccess.invoke(body)
    }

    override fun onFailure(call: Call<T>?, t: Throwable) {
        val error = t.message ?: return
        onError.invoke(ApiException(error))
    }
})

fun ImageView.byUrl(url: String) = Picasso.get().load(url).into(this)
