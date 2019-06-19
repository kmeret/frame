package io.github.kmeret.frame.data.network.user

import io.github.kmeret.frame.domain.repos.AuthRepo
import io.github.kmeret.frame.infrastructure.data.network.AuthException
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val authRepo: AuthRepo
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = authRepo.getAuthToken() ?: throw AuthException()

        val request = chain.request().newBuilder().apply {
            addHeader("Authorization", "Bearer $token")
        }.build()

        return chain.proceed(request)
    }

}