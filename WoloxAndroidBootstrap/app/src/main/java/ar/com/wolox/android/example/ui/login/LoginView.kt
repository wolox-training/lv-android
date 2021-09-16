package ar.com.wolox.android.example.ui.login

interface LoginView {

    fun openBrowser(url: String)

    fun toggleLoginButtonEnable(isEnable: Boolean)

    fun showErrorAllRequired()

    fun showErrorInvalidEmail()

    fun dismissKeyboard()

    fun goToHome()

    fun goToSignup()
}