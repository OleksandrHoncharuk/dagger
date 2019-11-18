package com.example.daggerpractice.displays

import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.widget.NestedScrollView
import com.bumptech.glide.Glide
import com.example.daggerpractice.R
import com.example.daggerpractice.displays.home.HomeFragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import dagger.android.support.DaggerAppCompatActivity
import de.hdodenhof.circleimageview.CircleImageView
import javax.inject.Inject


class MainActivity @Inject constructor() : DaggerAppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    private lateinit var appBarLayout: AppBarLayout
    private lateinit var nestedScrollView: NestedScrollView
    private lateinit var toolbar: Toolbar
    private lateinit var collapsedToolbar: CollapsingToolbarLayout
    private lateinit var image: CircleImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "Activity started")

        toolbar = findViewById(R.id.toolbar)
        appBarLayout = findViewById(R.id.appbar)
        nestedScrollView = findViewById(R.id.nested_scroll_view)
        collapsedToolbar = findViewById(R.id.collapsing_toolbar)
        image = findViewById(R.id.circle_image)

        setSupportActionBar(toolbar)

        val manager = supportFragmentManager

        expandCollapseAppBar(false)
        lockAppBar()

        if (manager.findFragmentByTag("home") == null) {
            Log.d(TAG, "create new home")
            manager
                .beginTransaction()
                .replace(R.id.relative_for_fragments, HomeFragment(), "home")
                .commit()
        }
    }

    fun setCircleImage(url: String) {
        Glide
            .with(this)
            .load(url)
            .into(image)
    }

    fun setToolbarParameters(showHomeAsUp: Boolean, title: String) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(showHomeAsUp)
        supportActionBar!!.title = title
        collapsedToolbar.title = title
    }

    fun expandCollapseAppBar(isExpanded: Boolean) {
        appBarLayout.setExpanded(isExpanded, false)
    }

    fun lockAppBar() {
        ViewCompat.setNestedScrollingEnabled(nestedScrollView, false)

        val layoutParams = appBarLayout.layoutParams as CoordinatorLayout.LayoutParams
        (layoutParams.behavior as? AppBarLayout.Behavior)?.setDragCallback(object : AppBarLayout.Behavior.DragCallback() {
            override fun canDrag(appBarLayout: AppBarLayout): Boolean {
                return false
            }
        })
    }

    fun unLockAppBar() {
        ViewCompat.setNestedScrollingEnabled(nestedScrollView, false)

        val layoutParams = appBarLayout.layoutParams as CoordinatorLayout.LayoutParams
        (layoutParams.behavior as? AppBarLayout.Behavior)?.setDragCallback(object : AppBarLayout.Behavior.DragCallback() {
            override fun canDrag(appBarLayout: AppBarLayout): Boolean {
                return true
            }
        })
    }

    override fun onBackPressed() {
        val backStackCount = supportFragmentManager.backStackEntryCount

        if (backStackCount > 0)
            supportFragmentManager.popBackStack()
        else
            super.onBackPressed()
    }
}