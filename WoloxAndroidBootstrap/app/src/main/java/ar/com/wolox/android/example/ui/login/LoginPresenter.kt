package ar.com.wolox.android.example.ui.login

import ar.com.wolox.android.example.model.dtos.LoginRequest
import ar.com.wolox.android.example.network.builder.networkRequest
import ar.com.wolox.android.example.network.repository.AuthRepository
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.android.example.utils.extensions.isEmailValid
import ar.com.wolox.wolmo.core.presenter.CoroutineBasePresenter
import javax.inject.Inject
import kotlinx.coroutines.launch

class LoginPresenter @Inject constructor(private val userSession: UserSession, private val authRepository: AuthRepository) : CoroutineBasePresenter<LoginView>() {

    fun onInit() {
        if (userSession.username != null) {
            view?.goToHome()
        } else {
            userSession.partialUsername?.let { view?.setPartialUsername(it) }
        }
    }

    fun onLoginButtonClicked(user: String, password: String) = launch {
        if (user.isEmpty() && password.isEmpty()) {
            view?.apply {
                dismissKeyboard()
                showErrorAllRequired()
            }
        } else if (!user.isEmailValid()) {
            view?.showErrorInvalidEmail()
        } else {
            view?.apply {
                dismissKeyboard()
                showLoading()
                toggleLoginButton(false)
            }
            networkRequest(authRepository.getLogin(LoginRequest(user, password))) {
                onResponseSuccessful {
                    userSession.username = user
                    view?.goToHome()
                }
                onResponseFailed { _, _ ->
                    view?.showErrorInvalidUser()
                }
                onCallFailure {
                    view?.showErrorNetwork()
                }
            }
            view?.apply {
                dismissLoading()
                toggleLoginButton(true)
            }
        }
    }

    fun onSignupButtonClicked() {
        view?.goToSignup()
    }

    fun onTermsClicked() = view?.openBrowser(WOLOX_URL)

    fun onUsernameChanged(partialUsername: String) {
        userSession.partialUsername = partialUsername
    }

    companion object {
        private const val WOLOX_URL = "https://www.wolox.com.ar"
    }
}