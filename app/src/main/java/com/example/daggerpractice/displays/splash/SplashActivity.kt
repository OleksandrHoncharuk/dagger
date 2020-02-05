package com.example.daggerpractice.displays.splash
//
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import androidx.lifecycle.MediatorLiveData
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.ViewModelProviders
//import com.example.daggerpractice.R
//import com.example.daggerpractice.displays.MainActivity
//import dagger.android.support.DaggerAppCompatActivity
//import javax.inject.Inject
//
//class SplashActivity @Inject constructor() : DaggerAppCompatActivity() {
//
//    @set:Inject
//    var viewModelFactory: ViewModelProvider.Factory? = null
//
//    private val TAG: String = SplashActivity::class.java.simpleName
//
//    private lateinit var splashViewModel: SplashViewModel
//    private var mainIntent: Intent? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash)
//        mainIntent = Intent(this, MainActivity::class.java)
//
//        splashViewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel::class.java)
//
//        setObserversToViewModel()
//    }
//
//    private fun setObserversToViewModel() {
//        splashViewModel.users.observe(this, Observer {
//            Log.d(TAG, "set user observer start")
//            if (it.size >= 20) startMainActivity()
//            else setMediatorLiveDataObserverOnDataSource()
//            Log.d(TAG, "set user observer finish")
//        })
//    }
//
//    private fun setMediatorLiveDataObserverOnDataSource() {
//        Log.d(TAG, "start mediator")
//        val mediator: MediatorLiveData<Boolean> = MediatorLiveData()
//
//        Log.d(TAG, "set first source to mediator")
//        mediator.addSource(splashViewModel.images) {
//            Log.d(TAG, "set image observer start")
//            splashViewModel.handleAndSaveImage(it)
//            if (splashViewModel.isDownloadComplete())
//                mediator.value = true
//            Log.d(TAG, "set image observer finish")
//        }
//
//        Log.d(TAG, "set second source to mediator")
//        mediator.addSource(splashViewModel.text) {
//            Log.d(TAG, "set text observer start")
//            splashViewModel.handleAndSaveRandomText(it)
//            if (splashViewModel.isDownloadComplete())
//                mediator.value = true
//            Log.d(TAG, "set text observer finish")
//        }
//
//        mediator.observe(this, Observer {
//            Log.d(TAG, "start mediator observer")
//            splashViewModel.handleAndSaveData()
//        })
//    }
//
//    private fun startMainActivity() {
//        Log.d(TAG, "start MainActivity")
//        startActivity(Intent(this, MainActivity::class.java))
//    }
//}
