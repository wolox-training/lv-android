package ar.com.wolox.android.example.ui.home.news.list.adapter

import androidx.recyclerview.widget.RecyclerView

import androidx.recyclerview.widget.LinearLayoutManager

abstract class EndlessRecyclerOnScrollListener() : RecyclerView.OnScrollListener() {
    private var loading = true
    private var previousTotal = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0
    private var lastVisibleItemPosition = 0

    fun reset() {
        previousTotal = 0
        loading = true
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        visibleItemCount = recyclerView.childCount

        with((recyclerView.layoutManager as LinearLayoutManager)) {
            lastVisibleItemPosition = findLastVisibleItemPosition()
            totalItemCount = itemCount
        }

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }

        if (!loading && (lastVisibleItemPosition + LOAD_THRESHOLD) > (totalItemCount - visibleItemCount)) {
            loading = true
            onLoadMore()
        }
    }

    abstract fun onLoadMore()

    companion object {
        const val LOAD_THRESHOLD = 5
    }
}
