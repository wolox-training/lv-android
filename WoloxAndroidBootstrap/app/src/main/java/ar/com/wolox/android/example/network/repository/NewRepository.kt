package ar.com.wolox.android.example.network.repository

import ar.com.wolox.android.example.network.services.NewService
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import ar.com.wolox.wolmo.networking.retrofit.handler.NetworkRequestHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewRepository @Inject constructor(private val retrofitServices: RetrofitServices) {

    private val service: NewService
        get() = retrofitServices.getService(NewService::class.java)

    suspend fun getNews(page: Int) = withContext(Dispatchers.IO) {
        NetworkRequestHandler.safeApiCall { service.getNews(page) }
    }
}