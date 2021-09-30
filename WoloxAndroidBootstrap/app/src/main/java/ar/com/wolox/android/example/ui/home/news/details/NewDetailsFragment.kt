package ar.com.wolox.android.example.ui.home.news.details

import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.core.os.bundleOf
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentNewDetailsBinding
import ar.com.wolox.android.example.BaseConfiguration.Companion.API_TIME_FORMAT
import ar.com.wolox.android.example.model.New
import ar.com.wolox.android.example.utils.Extras
import ar.com.wolox.android.example.utils.extensions.glideload
import ar.com.wolox.android.example.utils.extensions.showSnackbar
import ar.com.wolox.android.example.utils.extensions.toPrettyDate
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class NewDetailsFragment @Inject constructor() : WolmoFragment<FragmentNewDetailsBinding, NewDetailsPresenter>(), NewDetailsView {

    override fun layout() = R.layout.fragment_new_details

    override fun handleArguments(arguments: Bundle?) = arguments?.containsKey(Extras.BundleFragmentDetails.NEW)

    override fun init() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_back_white)

        presenter.onInit(requireArgument(Extras.BundleFragmentDetails.NEW) as New)
    }

    override fun setListeners() {
        with(binding) {

            toolbar.setNavigationOnClickListener {
                activity?.onBackPressed()
            }

            swipeLayout.setOnRefreshListener {
                presenter.onRefresh()
            }

            likedImage.setOnClickListener {
                presenter.onLikedClicked()
            }

            coverImage.setOnClickListener {
                presenter.onCoverImageClicked()
            }
        }
    }

    override fun setupNew(new: New) {
        with(binding) {
            nameText.text = new.commenter
            descriptionText.text = new.comment
            new.updatedAt.toPrettyDate(API_TIME_FORMAT)?.let {
                timeText.text = it
            }
            coverImage.glideload(new.avatar) // will fail, api images are dead
        }
    }

    override fun showError() {
        showSnackbar(getString(R.string.error_refreshing_generic), Snackbar.LENGTH_LONG)
    }

    override fun showNetworkError() {
        showSnackbar(getString(R.string.error_network_generic), Snackbar.LENGTH_LONG)
    }

    override fun toggleLikedButton(liked: Boolean) {
        binding.likedImage.isSelected = liked
    }

    override fun toggleEnabledLikedButton(enabled: Boolean) {
        binding.likedImage.isEnabled = enabled
    }

    override fun dismissRefreshing() {
        binding.swipeLayout.isRefreshing = false
    }

    override fun displayImageFullscreen() {
        with(binding) {
            val lp = this.coverImage.layoutParams
            lp.height = MATCH_PARENT
            this.coverImage.layoutParams = lp

            toolbar.visibility = View.GONE
        }
    }
    override fun dismissImageFullscreen() {
        with(binding) {
            val lp = this.coverImage.layoutParams
            lp.height = 0
            this.coverImage.layoutParams = lp

            toolbar.visibility = View.VISIBLE
        }
    }

    companion object {
        fun newInstance(new: New) = NewDetailsFragment().apply {
            arguments = bundleOf(Extras.BundleFragmentDetails.NEW to new)
        }
    }
}