package com.example.daggerpractice.displays.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daggerpractice.data.Repository
import com.example.daggerpractice.data.persistance.model.User
import com.example.daggerpractice.displays.home.adapter.HomeAdapter
import com.example.daggerpractice.displays.home.adapter.ItemData
import com.example.daggerpractice.displays.home.adapter.ListPresenter
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: Repository): ViewModel(), HomePresenter {

    private val TAG = HomeViewModel::class.java.simpleName

    private val _users: MutableLiveData<List<User>> = MutableLiveData()
    val users: LiveData<List<User>> get() = _users

    private var adapter: HomeAdapter
    private var items = ArrayList<ItemData>()

    init {
        _users.value = repository.getAllUsers()
        adapter = HomeAdapter(ListPresenter(items))
    }

    override fun getAllUsers(): LiveData<List<User>> {
        val liveData = MutableLiveData<List<User>>()
        liveData.value = repository.getAllUsers()
        return liveData
    }

    override fun onUsersChange(users: List<User>) {
        Log.d(TAG, "users list changed")
        users.forEach { user ->
            items.add(ItemData(user.url, user.title, user.text))
        }

        adapter.refresh(items)
        adapter.notifyDataSetChanged()
    }

    override fun getAdapter(): HomeAdapter {
        return adapter
    }
}