package ar.com.wolox.android.example.ui.home.news.details

import ar.com.wolox.android.example.model.New

interface NewDetailsView {

    fun setupNew(new: New)
    fun showError()
    fun showNetworkError()
    fun toggleLikedButton(liked: Boolean)
    fun toggleEnabledLikedButton(enabled: Boolean)
    fun dismissRefreshing()
    fun displayImageFullscreen()
    fun dismissImageFullscreen()
}