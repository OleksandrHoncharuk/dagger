package com.example.daggerpractice.data.persistance.repository.database

import com.example.daggerpractice.data.persistance.repository.database.dao.UserDao

interface DatabaseRepository {

    fun userDao(): UserDao
}
