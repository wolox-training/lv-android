package ar.com.wolox.android.example.ui.home.news.details

import android.content.Context
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.ActivityBaseBinding
import ar.com.wolox.android.example.model.New
import ar.com.wolox.android.example.utils.Extras
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import ar.com.wolox.wolmo.core.util.jumpTo
import javax.inject.Inject

class NewDetailsActivity @Inject constructor() : WolmoActivity<ActivityBaseBinding>() {

    override fun layout() = R.layout.activity_base

    override fun init() {
        replaceFragment(binding.activityBaseContent.id, NewDetailsFragment.newInstance(requireArgument(Extras.BundleFragmentDetails.NEW)))
    }

    companion object {

        fun start(context: Context, new: New) = context.jumpTo(
                NewDetailsActivity::class.java,
                Extras.BundleFragmentDetails.NEW to new)
    }
}