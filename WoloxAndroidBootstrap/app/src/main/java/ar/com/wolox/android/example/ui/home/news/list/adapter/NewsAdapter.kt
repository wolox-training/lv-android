package ar.com.wolox.android.example.ui.home.news.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.New
import ar.com.wolox.android.example.utils.extensions.glideload
import ar.com.wolox.android.example.utils.extensions.toPrettyDate

class NewsAdapter(private val listener: NewsListener) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var news = mutableListOf<New>()

    interface NewsListener {
        fun onClicked(id: Int)
    }

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
            liked.isEnabled = news[position].likes.isNotEmpty()

            root.setOnClickListener { listener.onClicked(news[position].id) }
        }
    }

    override fun getItemCount() = news.size

    fun addNews(items: List<New>) {
        news.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        news.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root = view.rootView
        val image: ImageView = view.findViewById(R.id.newImage)
        val time: TextView = view.findViewById(R.id.timeText)
        val title: TextView = view.findViewById(R.id.titleText)
        val description: TextView = view.findViewById(R.id.descriptionText)
        val liked: ImageView = view.findViewById(R.id.likedImage)
    }
}