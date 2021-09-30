package ar.com.wolox.android.example.ui.home.news.list

import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentNewsBinding
import ar.com.wolox.android.example.model.New
import ar.com.wolox.android.example.ui.home.news.details.NewDetailsActivity
import ar.com.wolox.android.example.ui.home.news.list.adapter.EndlessRecyclerOnScrollListener
import ar.com.wolox.android.example.ui.home.news.list.adapter.NewsAdapter
import ar.com.wolox.android.example.utils.extensions.showSnackbar
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class NewsFragment @Inject constructor() : WolmoFragment<FragmentNewsBinding, NewsPresenter>(), NewsView, SwipeRefreshLayout.OnRefreshListener, NewsAdapter.NewsListener {

    override fun layout() = R.layout.fragment_news

    lateinit var scrollListener: EndlessRecyclerOnScrollListener

    override fun init() {

        scrollListener = object : EndlessRecyclerOnScrollListener() {
            override fun onLoadMore() {
                presenter.loadNews()
            }
        }

        with(binding) {
            swipeLayout.setOnRefreshListener(this@NewsFragment)
            newList.addOnScrollListener(scrollListener)
        }

        presenter.onInit()
    }

    override fun setupNews(userId: Int) {
        binding.newList.adapter = NewsAdapter(userId, this@NewsFragment)
    }

    override fun addNews(news: List<New>) {
        with(binding.newList.adapter as NewsAdapter) {
            this.addNews(news)
        }
    }

    override fun clearList() {
        (binding.newList.adapter as NewsAdapter).clear()
        scrollListener.reset()
    }

    override fun toggleInitialLoading(visible: Boolean) {
        binding.loadingBar.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun toggleBottomLoading(visible: Boolean) {
        binding.bottomBar.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun showError() {
        showSnackbar(getString(R.string.fragment_news_error_request), Snackbar.LENGTH_LONG)
    }

    override fun showErrorNetwork() {
        showSnackbar(getString(R.string.error_network_generic), Snackbar.LENGTH_LONG)
    }

    override fun dismissRefreshing() {
        binding.swipeLayout.isRefreshing = false
    }

    override fun openDetails(new: New) {
        NewDetailsActivity.start(requireContext(), new)
    }

    override fun onRefresh() {
        presenter.onRefresh()
    }

    override fun onClicked(new: New) {
        presenter.onClicked(new)
    }

    override fun onResume() {
        super.onResume()
        presenter.onRefresh()
    }

    companion object {
        fun newInstance() = NewsFragment()
    }
}