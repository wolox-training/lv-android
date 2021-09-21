package ar.com.wolox.android.example.ui.home

import android.widget.LinearLayout
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentHomeBinding
import ar.com.wolox.android.example.ui.home.news.NewsFragment
import ar.com.wolox.android.example.ui.home.profile.ProfileFragment
import ar.com.wolox.wolmo.core.adapter.viewpager.SimpleFragmentPagerAdapter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import javax.inject.Inject
import dagger.Lazy

class HomeFragment private constructor() : WolmoFragment<FragmentHomeBinding, HomePresenter>(), HomeView {

    @Inject
    internal lateinit var newsFragment: NewsFragment

    @Inject
    internal lateinit var profileFragment: Lazy<ProfileFragment>

    override fun layout() = R.layout.fragment_home

    override fun init() {
        setTabLayout()
    }

    private fun setTabLayout() {
        binding.viewPager.adapter = SimpleFragmentPagerAdapter(childFragmentManager).apply {
            addFragments(
                    newsFragment to getString(R.string.news_toolbar_title),
                    profileFragment.get() to getString(R.string.profile_toolbar_title)
            )
        }
        binding.tabLayout.apply {
            binding.tabLayout.setupWithViewPager(binding.viewPager)
            getTabAt(0)?.setIcon(R.drawable.selector_tab_news)
            getTabAt(1)?.setIcon(R.drawable.selector_tab_profile)

            // Remove space between icon and text... Yeah, I don't like it either.
            for (indexTab in 0..this.tabCount) {
                val params = this.getTabAt(indexTab)?.view?.getChildAt(0)?.layoutParams as LinearLayout.LayoutParams?
                params?.bottomMargin = 0
                this.getTabAt(indexTab)?.view?.getChildAt(0)?.layoutParams = params
            }
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}