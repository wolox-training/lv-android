package ar.com.wolox.android.example.model.dtos

data class NewDetailsResponse(
    val commenter: String,
    val comment: String,
    val date: String,
    val avatar: String,
    val likes: List<Int>
)