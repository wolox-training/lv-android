package ar.com.wolox.android.example.ui.login

import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.android.example.utils.isEmailValid
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class LoginPresenter @Inject constructor(private val userSession: UserSession) : BasePresenter<LoginView>() {

    fun onInit() {
        if (userSession != null) {
            view?.goToHome()
        }
    }

    fun onLoginButtonClicked(user: String, password: String) {
        if (user.isEmpty() && password.isEmpty()) {
            view?.apply {
                dismissKeyboard()
                showErrorAllRequired()
            }
            return
        }
        if (!user.isEmailValid()) {
            view?.showErrorInvalidEmail()
            return
        }
        userSession.username = user
        view?.goToHome()
    }

    fun onSignupButtonClicked() {
        view?.goToSignup()
    }

    fun onTermsClicked() = view?.openBrowser(WOLOX_URL)

    companion object {
        private const val WOLOX_URL = "https://www.wolox.com.ar"
    }
}