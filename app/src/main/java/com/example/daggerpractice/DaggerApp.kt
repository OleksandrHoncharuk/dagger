package com.example.daggerpractice

import android.app.Application
import android.content.Context

class DaggerApp: Application() {

    companion object{
        fun getApp(context: Context): DaggerApp {
            return context.applicationContext as DaggerApp
        }
    }

    override fun onCreate() {
        super.onCreate()
        buildGraphAndInject()
    }

    private fun buildGraphAndInject() {

    }

    fun getAppComponent() {

    }
}