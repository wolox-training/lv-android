package ar.com.wolox.android.example.ui.login

import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentLoginBinding
import ar.com.wolox.android.example.ui.home.HomeActivity
import ar.com.wolox.android.example.ui.signup.SignupActivity
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.openBrowser
import ar.com.wolox.wolmo.core.util.jumpTo
import ar.com.wolox.android.example.utils.hideKeyboard

class LoginFragment private constructor() : WolmoFragment<FragmentLoginBinding, LoginPresenter>(), LoginView {

    override fun layout() = R.layout.fragment_login

    override fun init() {
        presenter.onInit()
    }

    override fun setListeners() {
        with(binding) {
            terms.setOnClickListener { presenter.onTermsClicked() }
            loginButton.setOnClickListener {
                presenter.onLoginButtonClicked(usernameInput.text.toString(), passwordInput.text.toString())
            }
            signupButton.setOnClickListener { presenter.onSignupButtonClicked() }
        }
    }

    override fun toggleLoginButtonEnable(isEnable: Boolean) {
        binding.loginButton.isEnabled = isEnable
    }

    override fun openBrowser(url: String) = requireContext().openBrowser(url)

    override fun showErrorAllRequired() {
        binding.usernameInput.error = getString(R.string.fragment_login_error_all_fields_required)
    }

    override fun dismissKeyboard() {
        hideKeyboard()
    }
    override fun showErrorInvalidEmail() {
        binding.usernameInput.error = getString(R.string.fragment_login_error_invalid_email)
    }

    override fun goToHome() {
        requireContext().jumpTo(HomeActivity::class.java)
    }

    override fun goToSignup() {
        requireContext().jumpTo(SignupActivity::class.java)
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}