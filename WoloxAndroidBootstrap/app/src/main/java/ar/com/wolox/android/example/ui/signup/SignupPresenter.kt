package ar.com.wolox.android.example.ui.signup

import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class SignupPresenter @Inject constructor(private val userSession: UserSession) : BasePresenter<SignupView>()