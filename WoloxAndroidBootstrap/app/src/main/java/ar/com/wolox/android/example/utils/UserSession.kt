package ar.com.wolox.android.example.utils

import ar.com.wolox.android.example.model.AuthInfo
import ar.com.wolox.wolmo.core.di.scopes.ApplicationScope
import ar.com.wolox.wolmo.core.util.SharedPreferencesManager
import com.google.gson.Gson

import javax.inject.Inject

@ApplicationScope
class UserSession @Inject constructor(private val sharedPreferencesManager: SharedPreferencesManager) {

    // Really, we don't need to query the username because this instance live as long as the
    // application, but we should add a check in case Android decides to kill the application
    // and return to a state where this isn't initialized.
    var username: String? = null
        get() = field ?: sharedPreferencesManager[Extras.UserLogin.USERNAME, null].also {
            field = it
        }
        set(username) {
            field = username
            sharedPreferencesManager.store(Extras.UserLogin.USERNAME, username)
        }

    var partialUsername: String? = null
        get() = field ?: sharedPreferencesManager[Extras.UserLogin.PARTIAL_USERNAME, null].also {
            field = it
        }
        set(partialUsername) {
            field = partialUsername
            sharedPreferencesManager.store(Extras.UserLogin.PARTIAL_USERNAME, partialUsername)
        }

    var authInfo: AuthInfo? = null
        get() = field
                ?: Gson().fromJson(sharedPreferencesManager[Extras.UserLogin.AUTH_INFO, null], AuthInfo::class.java).also {
                    field = it
                }
        set(authInfo) {
            field = authInfo
            sharedPreferencesManager.store(Extras.UserLogin.AUTH_INFO, Gson().toJson(authInfo))
        }
}
