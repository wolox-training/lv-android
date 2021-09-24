package ar.com.wolox.android.example.model.dtos

import ar.com.wolox.android.example.model.New
import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("page") val news: List<New>,
    @SerializedName("count") val count: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("current_page") val currentPage: Int,
    @SerializedName("previous_page") val previousPage: Int,
    @SerializedName("next_page") val nextPage: Int,
    @SerializedName("next_page_url") val nextPageUrl: String,
    @SerializedName("previous_page_url") val previousPageUrl: String
)