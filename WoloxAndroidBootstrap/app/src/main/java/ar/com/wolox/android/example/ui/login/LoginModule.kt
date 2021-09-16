package ar.com.wolox.android.example.ui.login

import dagger.android.ContributesAndroidInjector
import dagger.Module

@Module
abstract class LoginModule {

    @ContributesAndroidInjector
    internal abstract fun loginActivity(): LoginActivity

    @ContributesAndroidInjector
    internal abstract fun loginFragment(): LoginFragment
}