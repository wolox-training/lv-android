package ar.com.wolox.android.example.ui.home.news.details

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NewDetailsModule {

    @ContributesAndroidInjector
    internal abstract fun newDetailsActivity(): NewDetailsActivity

    @ContributesAndroidInjector
    internal abstract fun newDetailsFragment(): NewDetailsFragment
}