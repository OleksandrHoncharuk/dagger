package com.example.daggerpractice.data.persistance.repository.database

import com.example.daggerpractice.data.persistance.repository.database.dao.ImageDao
import com.example.daggerpractice.data.persistance.repository.database.dao.TextDao
import com.example.daggerpractice.data.persistance.repository.database.dao.UserDao

interface DatabaseRepository {

    fun userDao(): UserDao

    fun imageDao(): ImageDao

    fun textDao(): TextDao
}
