package ar.com.wolox.android.example.model

import ar.com.wolox.android.example.model.dtos.NewDetailsResponse
import java.io.Serializable

data class New(
    val id: Int,
    var commenter: String,
    var comment: String,
    var date: String,
    var avatar: String,
    val createdAt: String,
    val updatedAt: String,
    var likes: MutableList<Int>
) : Serializable {

    fun update(new: NewDetailsResponse) {
        commenter = new.commenter
        comment = new.comment
        date = new.date
        avatar = new.avatar
        likes = new.likes.toMutableList()
    }
}
