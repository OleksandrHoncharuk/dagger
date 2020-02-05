package com.example.daggerpractice.displays.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.*
import com.example.daggerpractice.DaggerApp
import com.example.daggerpractice.R
import com.example.daggerpractice.displays.MainActivity
import com.example.daggerpractice.displays.details.DetailsFragment
import com.example.daggerpractice.displays.home.adapter.HomeAdapter
import com.example.daggerpractice.displays.home.adapter.ItemData
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HomeFragment @Inject constructor() : DaggerFragment(), HomeView, HomeAdapter.OnItemClickedListener {

    private val TAG = HomeFragment::class.java.simpleName

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: HomeViewModel
    private lateinit var recycler: RecyclerView

    override fun onResume() {
        (activity as MainActivity).setToolbarParameters(false, DaggerApp::class.java.simpleName)
        super.onResume()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.home_fragment_layout, container, false)
        Log.d(TAG, "Start home")

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)

        recycler = root.findViewById(R.id.recycler)
        val manager = LinearLayoutManager(context)
        recycler.layoutManager = manager
        recycler.addItemDecoration(DividerItemDecoration(context, manager.orientation))

        Log.d(TAG, "Start observe viewModel")
        viewModel.users.observe(this, Observer {
            Log.d(TAG, "Start observer")
            viewModel.onUsersChange(it)
            setRecyclerViewAdapter()
        })

        return root
    }

    private fun setRecyclerViewAdapter() {
        val adapter = viewModel.getAdapter()
        recycler.adapter = adapter
        adapter.setOnItemClickedListener(this)
        recycler.itemAnimator = DefaultItemAnimator()
        ItemTouchHelper(SwipeCallback(adapter)).attachToRecyclerView(recycler)
        Log.d(TAG, "set up recycler")
    }

    override fun onClicked(itemData: ItemData) {
        (activity as MainActivity).expandCollapseAppBar(true)
        (activity as MainActivity).unLockAppBar()
        activity!!.supportFragmentManager
            .beginTransaction()
            .addToBackStack("details")
            .replace(R.id.relative_for_fragments,
                createDetailsFragment(itemData),
                DetailsFragment::class.java.simpleName)
            .commit()
    }

    private fun createDetailsFragment(itemData: ItemData): DetailsFragment {
        return DetailsFragment.newInstance(itemData)
    }
}