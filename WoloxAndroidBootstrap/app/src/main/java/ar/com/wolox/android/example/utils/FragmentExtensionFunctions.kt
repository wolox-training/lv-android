package ar.com.wolox.android.example.utils

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackbar(message: String, length: Int = Snackbar.LENGTH_SHORT) = view?.run { Snackbar.make(this, message, length).show() }

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}