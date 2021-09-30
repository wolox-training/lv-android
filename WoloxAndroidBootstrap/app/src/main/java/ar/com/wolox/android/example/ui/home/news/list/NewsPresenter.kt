package ar.com.wolox.android.example.ui.home.news.list

import ar.com.wolox.android.example.model.New
import ar.com.wolox.android.example.network.builder.networkRequest
import ar.com.wolox.android.example.network.repository.NewRepository
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.CoroutineBasePresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsPresenter @Inject constructor(private val repository: NewRepository, private val userSession: UserSession) : CoroutineBasePresenter<NewsView>() {

    private var currentPage = INITIAL_PAGE

    fun onInit() {
        userSession.user?.id?.let {
            view?.setupNews(it)
            onRefresh()
        }
    }

    fun loadNews() {
        view?.toggleBottomLoading(true)
        makeRequest(++currentPage)
    }

    fun onRefresh() {
        currentPage = INITIAL_PAGE
        view?.clearList()
        makeRequest()
    }

    private fun makeRequest(page: Int = 1) = launch {
        networkRequest(repository.getNews(page)) {
            onResponseSuccessful { response ->
                currentPage = page
                response?.let {
                    view?.addNews(it.news)
                }
            }

            onResponseFailed { _, _ -> view?.showError() }

            onCallFailure { view?.showErrorNetwork() }

            view?.apply {
                dismissRefreshing()
                toggleInitialLoading(false)
                toggleBottomLoading(false)
            }
        }
    }

    fun onClicked(new: New) {
        view?.openDetails(new)
    }

    companion object {
        private const val INITIAL_PAGE = 1
    }
}