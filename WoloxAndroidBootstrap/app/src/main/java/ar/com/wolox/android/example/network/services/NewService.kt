package ar.com.wolox.android.example.network.services

import ar.com.wolox.android.example.model.dtos.NewDetailsResponse
import ar.com.wolox.android.example.model.dtos.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface NewService {

    @GET("/comments")
    suspend fun getNews(@Query("page") page: Int): Response<NewsResponse>

    @GET("/comments/{id}")
    suspend fun getSingleNew(@Path("id") id: Int): Response<NewDetailsResponse>

    @PUT("/comments/like/{id}")
    suspend fun toggleLike(@Path("id") id: Int): Response<Unit>
}
