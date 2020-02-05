package com.example.daggerpractice.displays.home

import android.util.Log
import androidx.lifecycle.*
import com.example.daggerpractice.data.Repository
import com.example.daggerpractice.data.persistance.model.User
import com.example.daggerpractice.displays.home.adapter.HomeAdapter
import com.example.daggerpractice.displays.home.adapter.ItemData
import com.example.daggerpractice.displays.home.adapter.ListPresenter
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: Repository): ViewModel(), HomePresenter {

    private val TAG = HomeViewModel::class.java.simpleName

    private val _users: MutableLiveData<List<User>> = MutableLiveData()
    lateinit var users: LiveData<List<User>>

    private var adapter: HomeAdapter
    private var items = ArrayList<ItemData>()

    init {
        users = repository.getUsersLiveData()
        adapter = HomeAdapter(ListPresenter(items))
    }

    override fun getAllUsers(): LiveData<List<User>> {
        return liveData {
            val data = repository.getAllUsers()
            emit(data)
        }
    }

    override fun onUsersChange(users: List<User>) {
        Log.d(TAG, "users list changed. List size is ${users.size}")
        users.forEach { user ->
            Log.d(TAG, "user added to items")
            items.add(ItemData(user.id, user.url, user.title, user.text))
        }

        adapter.refresh(items)
        adapter.notifyDataSetChanged()
    }

    override fun getAdapter(): HomeAdapter {
        Log.d(TAG, "adapter is not null ${adapter}")
        return adapter
    }
}