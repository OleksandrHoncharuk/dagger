package com.example.daggerpractice.displays

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.example.daggerpractice.R
import com.example.daggerpractice.data.Repository
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainActivity @Inject constructor() : DaggerAppCompatActivity() {

    @set:Inject
    var repository: Repository? = null

//    private lateinit var mJob: Job
//    override val coroutineContext: CoroutineContext
//    get() = mJob + Dispatchers.Main



    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "Activity started")

//        mJob = Job()
//
//        launch {
//            val deferred = async(Dispatchers.Default) {
//                repository!!.deleteAllUsers()
//                Log.d("MainActivity", "Users deleted")
//            }
//
//            deferred.await()
//        }
    }

    override fun onDestroy() {
        super.onDestroy()

//        mJob.cancel()
    }
}