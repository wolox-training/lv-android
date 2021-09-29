package ar.com.wolox.android.example.ui.home.news.list

import ar.com.wolox.android.example.model.New

interface NewsView {
    fun setupNews(userId: Int)
    fun addNews(news: List<New>)
    fun clearList()
    fun toggleInitialLoading(visible: Boolean)
    fun toggleBottomLoading(visible: Boolean)
    fun showError()
    fun showErrorNetwork()
    fun dismissRefreshing()
    fun openDetails(new: New)
}