package com.example.daggerpractice.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.daggerpractice.data.client.RandomTextApiInterface
import com.example.daggerpractice.data.client.TheCatApiInterface
import com.example.daggerpractice.data.persistance.model.User
import com.example.daggerpractice.data.persistance.repository.database.dao.UserDao
import com.example.daggerpractice.data.pojo_models.image.ImageResponce
import com.example.daggerpractice.data.pojo_models.text.TextResponce


class Repository(
    private val randomTextApi: RandomTextApiInterface, private val theCatApi: TheCatApiInterface,
    private val userDao: UserDao
) {

    val textResponce: LiveData<TextResponce> = liveData {
        Log.d("Repository", "start download text")
        val data = randomTextApi.getRandomText()
        Log.d("Repository", "finish download text")
        emit(data)
    }


    val catsImage: LiveData<List<ImageResponce>> = liveData {
        Log.d("Repository", "start download image")
        val data = theCatApi.getCatsImage()
        Log.d("Repository", "finish download image")
        emit(data)
    }

    val users: LiveData<List<User>> = liveData {
        Log.d("Repository", "start get user from database")
        val data = userDao.getAll()
        Log.d("Repository", "finish get user from database")
        emit(data)
    }

    fun insertNewUser(user: User) = userDao.insert(user)

    fun deleteUser(user: User) = userDao.delete(user)

    fun findByTitle(title: String) = userDao.findByTitle(title)

    fun findById(id: String) = userDao.findById(id)

}