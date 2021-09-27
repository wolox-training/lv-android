package ar.com.wolox.android.example.utils.extensions

import android.text.TextUtils
import android.util.Patterns
import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

fun String.isEmailValid(): Boolean {
    return !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

const val TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
fun String.toPrettyDate(): String? {
    return try {
        PrettyTime()
            .format(Date(
                SimpleDateFormat(TIME_FORMAT).parse(this).time
            ))
    } catch (e: ParseException) {
        null
    }
}