package com.example.daggerpractice

import com.example.daggerpractice.data.Repository
import com.example.daggerpractice.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class DaggerApp : DaggerApplication() {

    @set:Inject
    var repository: Repository? = null

    override fun onCreate() {
        super.onCreate()

        //todo Add Corutines for API and room
    }


    override fun applicationInjector(): AndroidInjector<out DaggerApp> {
        return DaggerAppComponent.builder().create(this)
    }


}