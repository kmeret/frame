package io.github.kmeret.base.network

import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Picasso
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiException : Exception()

fun <T> Call<T>.makeRequest(onSuccess: (response: T) -> Unit,
                            onError: (ex: ApiException) -> Unit) = enqueue(object : Callback<T> {

    override fun onResponse(call: Call<T>?, response: Response<T>) {
        if (!response.isSuccessful) {
            val body = response.errorBody()?.string()
            if (body == null) {
                Log.d("RetrofitError", "Null error body!")
                onError.invoke(ApiException())
                return
            }
            try {
                Log.d("RetrofitError", JSONObject(body).getString("message"))
                onError.invoke(ApiException())
                return
            } catch (ex: Exception) {
                Log.d("RetrofitError", ex.localizedMessage)
                onError.invoke(ApiException())
            }
        }

        val body = response.body()
        if (body == null) {
            Log.d("RetrofitError", "Null response body!")
            onError.invoke(ApiException())
            return
        }

        onSuccess.invoke(body)
    }

    override fun onFailure(call: Call<T>?, t: Throwable) {
        val error = t.message ?: return
        Log.d("RetrofitError", error)
        onError.invoke(ApiException())
    }
})

fun ImageView.loadWithUrl(url: String) = Picasso.get().load(url).into(this)
