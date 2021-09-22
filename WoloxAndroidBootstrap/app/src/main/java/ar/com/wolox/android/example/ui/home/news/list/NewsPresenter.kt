package ar.com.wolox.android.example.ui.home.news.list

import ar.com.wolox.android.example.network.repository.NewRepository
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class NewsPresenter @Inject constructor(private val newsRepo: NewRepository) : BasePresenter<NewsView>() {

    fun onInit() {
        view?.apply {
            showLoading()
            addNews(newsRepo.getNews())
            dismissLoading()
        }
    }
}