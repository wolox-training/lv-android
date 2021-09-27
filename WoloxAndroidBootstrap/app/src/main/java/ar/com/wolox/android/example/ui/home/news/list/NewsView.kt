package ar.com.wolox.android.example.ui.home.news.list

import ar.com.wolox.android.example.model.New

interface NewsView {
    fun addNews(news: List<New>)
    fun showLoading()
    fun dismissLoading()
}