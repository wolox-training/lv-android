package ar.com.wolox.android.example

internal open class BaseConfiguration {

    companion object {
        const val SHARED_PREFERENCES_NAME = "private-shared-prefs"
        const val BASE_URL = "https://w-android-training.herokuapp.com"
        const val AUTH_PATH = "/auth/sign_in"

        const val HEADER_TOKEN = "Access-Token"
        const val HEADER_CLIENT = "Client"
        const val HEADER_UID = "Uid"

        const val API_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    }
}
