package com.example.daggerpractice.displays.home

import androidx.lifecycle.LiveData
import com.example.daggerpractice.data.persistance.model.User
import com.example.daggerpractice.di.AppScope
import com.example.daggerpractice.displays.home.adapter.HomeAdapter

interface HomeView {
}

interface HomePresenter {

    fun getAllUsers(): LiveData<List<User>>

    fun onUsersChange(users: List<User>)

    fun getAdapter(): HomeAdapter
}