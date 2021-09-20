package ar.com.wolox.android.example.network.repository

import ar.com.wolox.android.example.model.dtos.LoginRequest
import ar.com.wolox.android.example.network.services.AuthService
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import ar.com.wolox.wolmo.networking.retrofit.handler.NetworkRequestHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepository @Inject constructor(private val retrofitServices: RetrofitServices) {

    private val service: AuthService
        get() = retrofitServices.getService(AuthService::class.java)

    suspend fun getLogin(login: LoginRequest) = withContext(Dispatchers.IO) {
        NetworkRequestHandler.safeApiCall { service.login(login) }
    }
}