package ar.com.wolox.android.example.utils.extensions

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.glideload(url: String) {
    Glide.with(this)
        .load(url)
        .placeholder(ColorDrawable(Color.GRAY))
        .into(this)
}