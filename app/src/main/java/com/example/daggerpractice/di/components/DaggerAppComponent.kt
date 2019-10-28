package com.example.daggerpractice.di.components

import com.example.daggerpractice.DaggerApp
import com.example.daggerpractice.di.builders.ActivityBuilderModule
import com.example.daggerpractice.di.modules.DaggerAppModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [DaggerAppModule::class, AndroidSupportInjectionModule::class, ActivityBuilderModule::class])
interface DaggerAppComponent : AndroidInjector<DaggerApp> {

    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<DaggerApp>()
}