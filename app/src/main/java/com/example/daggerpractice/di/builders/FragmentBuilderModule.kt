package com.example.daggerpractice.di.builders

import com.example.daggerpractice.displays.details.DetailsFragment
import com.example.daggerpractice.displays.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun contibuteHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    internal abstract fun contibuteDetailsFragment(): DetailsFragment
}