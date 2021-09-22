package ar.com.wolox.android.example.ui.home.news.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.New
import ar.com.wolox.android.example.utils.extensions.glideload
import ar.com.wolox.android.example.utils.extensions.toPrettyDate

class NewsAdapter(val news: List<New>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_new, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            title.text = news[position].commenter
            description.text = news[position].comment
            image.glideload(news[position].avatar)
            news[position].createdAt.toPrettyDate()?.let {
                time.text = it
            }
            liked.apply {
                if (news[position].likes.isEmpty())
                    setImageDrawable(ContextCompat.getDrawable(liked.context, R.drawable.ic_like_off))
                else
                    setImageDrawable(ContextCompat.getDrawable(liked.context, R.drawable.ic_like_on))
            }
        }
    }

    override fun getItemCount() = news.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root = view.rootView
        val image: ImageView = view.findViewById(R.id.newImage)
        val time: TextView = view.findViewById(R.id.timeText)
        val title: TextView = view.findViewById(R.id.titleText)
        val description: TextView = view.findViewById(R.id.descriptionText)
        val liked: ImageView = view.findViewById(R.id.likedImage)
    }
}