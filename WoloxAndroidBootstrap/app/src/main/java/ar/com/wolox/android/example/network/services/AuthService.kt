package ar.com.wolox.android.example.network.services

import ar.com.wolox.android.example.BaseConfiguration.Companion.AUTH_PATH
import ar.com.wolox.android.example.model.dtos.LoginRequest
import ar.com.wolox.android.example.model.dtos.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST(AUTH_PATH)
    suspend fun login(@Body login: LoginRequest): Response<LoginResponse>
}