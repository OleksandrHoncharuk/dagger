package com.example.daggerpractice.displays.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.daggerpractice.R
import com.example.daggerpractice.displays.MainActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SplashActivity @Inject constructor() : DaggerAppCompatActivity() {

    @set:Inject
    var viewModelFactory: ViewModelProvider.Factory? = null

    private val tag: String = "SplashActivity"

    private lateinit var splashViewModel: SplashViewModel
    private var mainIntent: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mainIntent = Intent(this, MainActivity::class.java)

        splashViewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel::class.java)

        setObserversToViewModel()
    }

    private fun setObserversToViewModel() {
        splashViewModel.users.observe(this, Observer {
            Log.d(tag, "set user observer start")
            if (it.size >= 20) startMainActivity()
            Log.d(tag, "set user observer finish")
        })

        setMediatorLiveDataObserverOnDataSource()
    }

    private fun setMediatorLiveDataObserverOnDataSource() {
        Log.d(tag, "start mediator")
        val mediator: MediatorLiveData<Boolean> = MediatorLiveData()

        Log.d(tag, "set first source to mediator")
        mediator.addSource(splashViewModel.images) {
            Log.d(tag, "set image observer start")
            splashViewModel.handleAndSaveImage(it)
            if (splashViewModel.isDownloadComplete())
                mediator.value = true
            Log.d(tag, "set image observer finish")
        }

        Log.d(tag, "set second source to mediator")
        mediator.addSource(splashViewModel.text) {
            Log.d(tag, "set text observer start")
            splashViewModel.handleAndSaveRandomText(it)
            if (splashViewModel.isDownloadComplete())
                mediator.value = true
            Log.d(tag, "set text observer finish")
        }

        mediator.observe(this, Observer {
            Log.d(tag, "start mediator observer")
            splashViewModel.handleAndSaveData()
        })
    }

    fun startMainActivity() {
        Log.d(tag, "start MainActivity")
        startActivity(Intent(this, MainActivity::class.java))
    }
}
