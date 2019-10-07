package com.example.daggerpractice.persistance.repository.database

import com.example.daggerpractice.persistance.repository.database.dao.UserDao

interface DatabaseRepository {

    fun userDao(): UserDao
}
