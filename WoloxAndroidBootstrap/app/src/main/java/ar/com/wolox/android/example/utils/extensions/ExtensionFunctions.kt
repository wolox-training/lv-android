package ar.com.wolox.android.example.utils.extensions

fun <T : Any, R : Any> whenAllNotNull(vararg elements: T?, block: (List<T>) -> R) {
    if (elements.all { it != null }) {
        block(elements.filterNotNull())
    }
}