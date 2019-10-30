package com.example.daggerpractice

import com.example.daggerpractice.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class DaggerApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApp> {
        return DaggerAppComponent.builder().create(this)
    }
}