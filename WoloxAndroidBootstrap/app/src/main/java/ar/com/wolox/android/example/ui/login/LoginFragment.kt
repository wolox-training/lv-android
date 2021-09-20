package ar.com.wolox.android.example.ui.login

import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentLoginBinding
import ar.com.wolox.android.example.ui.home.HomeActivity
import ar.com.wolox.android.example.ui.signup.SignupActivity
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.openBrowser
import ar.com.wolox.wolmo.core.util.jumpTo
import ar.com.wolox.android.example.utils.hideKeyboard
import ar.com.wolox.android.example.utils.showToast

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
            usernameInput.doAfterTextChanged {
                presenter.onUsernameChanged(it.toString())
            }
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

    override fun setPartialUsername(username: String) {
        binding.usernameInput.setText(username)
    }

    override fun showLoading() {
        binding.loadingBar.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        binding.loadingBar.visibility = View.GONE
    }

    override fun toggleLoginButton(enabled: Boolean) {
        binding.loginButton.isEnabled = enabled
    }

    override fun showErrorInvalidUser() {
        showToast(getString(R.string.fragment_login_error_login_credentials), Toast.LENGTH_LONG)
    }

    override fun showErrorNetwork() {
        showToast(getString(R.string.fragment_login_error_network), Toast.LENGTH_LONG)
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}