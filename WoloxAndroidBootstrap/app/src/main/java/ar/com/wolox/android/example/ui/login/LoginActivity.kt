package ar.com.wolox.android.example.ui.login

import ar.com.wolox.android.databinding.ActivityBaseBinding
import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class LoginActivity : WolmoActivity<ActivityBaseBinding>() {
    override fun layout() = R.layout.activity_base

    override fun init() {
        replaceFragment(binding.activityBaseContent.id, LoginFragment.newInstance())
    }
}