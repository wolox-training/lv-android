package ar.com.wolox.android.example.ui.home.news.details

import ar.com.wolox.android.example.model.New
import ar.com.wolox.android.example.network.builder.networkRequest
import ar.com.wolox.android.example.network.repository.NewRepository
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.CoroutineBasePresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewDetailsPresenter @Inject constructor(private val newRepository: NewRepository, private val userSession: UserSession) : CoroutineBasePresenter<NewDetailsView>() {

    private lateinit var new: New

    private var imageFullscreen = false

    fun onInit(new: New) {
        this.new = new
        view?.apply {
            setupNew(new)
            toggleLikedButton(new.likes.contains(userSession.user?.id))
        }
    }

    fun onRefresh() = launch {
        networkRequest(newRepository.getSingleNew(new.id)) {
            onResponseSuccessful { response ->
                response?.let {
                    new.update(it)
                    view?.apply {
                        setupNew(new)
                        toggleLikedButton(new.likes.contains(userSession.user?.id))
                    }
                }
            }
            onResponseFailed { _, _ -> view?.showError() }
            onCallFailure { view?.showNetworkError() }
            view?.dismissRefreshing()
        }
    }

    fun onLikedClicked() = launch {
        view?.toggleEnabledLikedButton(false)
        networkRequest(newRepository.toggleLiked(new.id)) {
            onResponseSuccessful {
                userSession.user?.id?.let {
                    if (new.likes.contains(it)) {
                        new.likes.remove(it)
                    } else {
                        new.likes.add(it)
                    }
                    view?.toggleLikedButton(new.likes.contains(it))
                }
            }
            onResponseFailed { _, _ -> view?.showError() }
            onCallFailure { view?.showNetworkError() }
            view?.toggleEnabledLikedButton(true)
        }
    }

    fun onCoverImageClicked() {
        if (imageFullscreen)
            view?.dismissImageFullscreen()
        else
            view?.displayImageFullscreen()
        imageFullscreen = !imageFullscreen
    }
}
