package com.example.daggerpractice.displays

import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import com.example.daggerpractice.R
import com.example.daggerpractice.displays.home.HomeFragment
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity @Inject constructor() : DaggerAppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "Activity started")

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val manager = supportFragmentManager

        if (manager.findFragmentByTag("home") == null) {
            Log.d(TAG, "create new home")
            manager
                .beginTransaction()
                .replace(R.id.relative_for_fragments, HomeFragment(), "home")
                .commit()
        }
    }
}