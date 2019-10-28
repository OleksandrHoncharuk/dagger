package com.example.daggerpractice.data

import com.example.daggerpractice.data.client.RandomTextApiInterface
import com.example.daggerpractice.data.client.TheCatApiInterface
import com.example.daggerpractice.data.persistance.model.User
import com.example.daggerpractice.data.persistance.repository.database.dao.UserDao

class Repository(
    private val randomTextApi: RandomTextApiInterface,
    private val theCatApi: TheCatApiInterface, private val userDao: UserDao
) {

    suspend fun getRandomText() = randomTextApi.getRandomText()

    suspend fun getImagesUrls() = theCatApi.getCatsImage()

    suspend fun insertNewUser(user: User) = userDao.insert(user)

    suspend fun deleteUser(user: User) = userDao.delete(user)

}