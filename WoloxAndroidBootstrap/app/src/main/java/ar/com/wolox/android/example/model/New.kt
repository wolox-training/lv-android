package ar.com.wolox.android.example.model

data class New(
    val id: Int,
    val commenter: String,
    val comment: String,
    val date: String,
    val avatar: String,
    val createdAt: String,
    val updatedAt: String,
    val likes: List<Int>
)
