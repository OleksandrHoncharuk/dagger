package com.example.daggerpractice.data.persistance.repository.database

import android.content.Context

import androidx.annotation.VisibleForTesting
import com.example.daggerpractice.data.persistance.repository.database.dao.UserDao

class DatabaseRepositoryImpl(context: Context) : DatabaseRepository {
    private var db: AppDatabase? = null

    init {
        db = AppDatabase.build(context)
    }


    override fun userDao(): UserDao {
        return db!!.userDao()
    }

    @VisibleForTesting
    fun setDb(db: AppDatabase) {
        this.db = db
    }
}
