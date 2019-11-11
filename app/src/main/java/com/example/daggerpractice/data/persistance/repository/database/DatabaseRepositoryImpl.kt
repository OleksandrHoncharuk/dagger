package com.example.daggerpractice.data.persistance.repository.database

import android.content.Context

import androidx.annotation.VisibleForTesting
import com.example.daggerpractice.data.persistance.repository.database.dao.ImageDao
import com.example.daggerpractice.data.persistance.repository.database.dao.TextDao
import com.example.daggerpractice.data.persistance.repository.database.dao.UserDao

class DatabaseRepositoryImpl(context: Context) : DatabaseRepository {
    private var db: AppDatabase? = null

    init {
        db = AppDatabase.build(context)
    }


    override fun userDao(): UserDao {
        return db!!.userDao()
    }

    override fun imageDao(): ImageDao {
        return db!!.imageDao()
    }

    override fun textDao(): TextDao {
        return db!!.textDao()
    }

    @VisibleForTesting
    fun setDb(db: AppDatabase) {
        this.db = db
    }
}
