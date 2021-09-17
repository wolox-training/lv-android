package ar.com.wolox.android.example.ui.signup

import dagger.android.ContributesAndroidInjector
import dagger.Module

@Module
abstract class SignupModule {

    @ContributesAndroidInjector
    internal abstract fun signupActivity(): SignupActivity

    @ContributesAndroidInjector
    internal abstract fun signupFragment(): SignupFragment
}