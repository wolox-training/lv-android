package ar.com.wolox.android.example.network

import ar.com.wolox.android.example.BaseConfiguration.Companion.AUTH_PATH
import ar.com.wolox.android.example.utils.UserSession
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val userSession: UserSession) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if (!request.url.encodedPath.equals(AUTH_PATH, ignoreCase = true)) {
            userSession.authInfo?.let {
                request = request.newBuilder()
                        .addHeader("Access-Token", "Bearer token-here")
                        .addHeader("Client", "Bearer token-here")
                        .addHeader("Uuid", "Bearer token-here")
                        .build()
            }
        }
        return chain.proceed(request)
    }
}