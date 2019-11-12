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
import com.example.daggerpractice.R
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.home_fragment_layout, container, false)
        Log.d(TAG, "Start home")

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)

        recycler = root.findViewById(R.id.recycler)
        val manager = LinearLayoutManager(context)
        recycler.layoutManager = manager
        recycler.addItemDecoration(DividerItemDecoration(context, manager.orientation))

        viewModel.users.observe(this, Observer {
            viewModel.onUsersChange(it)
            setRecyclerViewAdapter()
        })

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun setRecyclerViewAdapter() {
        recycler.adapter = viewModel.getAdapter()
        viewModel.getAdapter().setOnItemClickedListener(this)
        recycler.itemAnimator = DefaultItemAnimator()
        ItemTouchHelper(SwipeCallback(viewModel.getAdapter())).attachToRecyclerView(recycler)
    }

    override fun onClicked(itemData: ItemData) {
        activity!!.supportFragmentManager
            .beginTransaction()
            .addToBackStack("Details")
            .replace(R.id.relative_for_fragments,
                createDetailsFragment(itemData),
                DetailsFragment::class.java.simpleName)
            .commit()
    }

    private fun createDetailsFragment(itemData: ItemData): DetailsFragment {
        return DetailsFragment.newInstance(itemData)
    }
}