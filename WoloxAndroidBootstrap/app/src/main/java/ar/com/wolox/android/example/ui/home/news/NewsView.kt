package ar.com.wolox.android.example.ui.home.news

import ar.com.wolox.android.example.model.New

interface NewsView{
    fun addNews(news: List<New>)
}