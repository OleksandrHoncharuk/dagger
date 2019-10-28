package com.example.daggerpractice.di.builders

import com.example.daggerpractice.displays.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    internal abstract fun mainActivity(): MainActivity
}