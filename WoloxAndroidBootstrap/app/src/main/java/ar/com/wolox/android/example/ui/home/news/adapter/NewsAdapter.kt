package ar.com.wolox.android.example.ui.home.news.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.databinding.ItemNewBinding
import ar.com.wolox.android.example.model.New

class NewsAdapter(val news: List<New>): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    override fun getItemCount() = news.size


    inner class ViewHolder(var binding: ItemNewBinding) : RecyclerView.ViewHolder(binding.root) {


    }
}