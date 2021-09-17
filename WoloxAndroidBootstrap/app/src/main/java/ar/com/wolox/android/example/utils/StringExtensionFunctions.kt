package ar.com.wolox.android.example.utils

import android.text.TextUtils
import android.util.Patterns

fun String.isEmailValid(): Boolean {
    return !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}