package ar.com.wolox.android.example.ui.home

import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.ActivityBaseBinding
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import javax.inject.Inject

class HomeActivity @Inject constructor() : WolmoActivity<ActivityBaseBinding>() {

    override fun layout() = R.layout.activity_base

    override fun init() {
        replaceFragment(binding.activityBaseContent.id, HomeFragment.newInstance())
    }
}