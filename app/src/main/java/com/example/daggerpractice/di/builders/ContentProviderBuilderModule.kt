package com.example.daggerpractice.di.builders

import com.example.daggerpractice.managers.WorkManagerInit
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ContentProviderBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun workManagerInit(): WorkManagerInit
}