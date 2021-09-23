package ar.com.wolox.android.example.utils.extensions

import android.widget.LinearLayout
import com.google.android.material.tabs.TabLayout

fun TabLayout.removeSpace() {
    for (indexTab in 0..this.tabCount) {
        val params = this.getTabAt(indexTab)?.view?.getChildAt(0)?.layoutParams as LinearLayout.LayoutParams?
        params?.bottomMargin = 0
        this.getTabAt(indexTab)?.view?.getChildAt(0)?.layoutParams = params
    }
}