package ar.com.wolox.android.example.ui.home.news.list

import android.view.View
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentNewsBinding
import ar.com.wolox.android.example.model.New
import ar.com.wolox.android.example.ui.home.news.list.adapter.NewsAdapter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import javax.inject.Inject

class NewsFragment @Inject constructor() : WolmoFragment<FragmentNewsBinding, NewsPresenter>(), NewsView {

    override fun layout() = R.layout.fragment_news

    override fun init() {
        presenter.onInit()
    }

    override fun addNews(news: List<New>) {
        binding.newList.adapter = NewsAdapter(news)
    }

    override fun showLoading() {
        binding.loadingBar.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        binding.loadingBar.visibility = View.GONE
    }

    companion object {
        fun newInstance() = NewsFragment()
    }
}