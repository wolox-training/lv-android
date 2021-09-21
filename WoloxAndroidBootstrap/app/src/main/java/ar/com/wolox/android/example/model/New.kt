package ar.com.wolox.android.example.model

import org.joda.time.DateTime

data class New(
    val id: Int,
    val commenter: String,
    val comment: String,
    val date: String,
    val avatar: String,
    val createdAt: DateTime

)
