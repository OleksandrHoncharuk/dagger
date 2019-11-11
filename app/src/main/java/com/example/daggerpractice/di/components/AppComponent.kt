package com.example.daggerpractice.di.components

import com.example.daggerpractice.DaggerApp
import com.example.daggerpractice.di.AppScope
import com.example.daggerpractice.di.builders.ActivityBuilderModule
import com.example.daggerpractice.di.builders.ContentProviderBuilderModule
import com.example.daggerpractice.di.modules.DaggerAppModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(modules = [DaggerAppModule::class, AndroidSupportInjectionModule::class, ActivityBuilderModule::class, ContentProviderBuilderModule::class])
interface AppComponent : AndroidInjector<DaggerApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DaggerApp>()
}