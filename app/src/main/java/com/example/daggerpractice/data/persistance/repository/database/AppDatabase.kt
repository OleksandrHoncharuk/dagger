package com.example.daggerpractice.data.persistance.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.daggerpractice.data.persistance.model.Image
import com.example.daggerpractice.data.persistance.model.Text
import com.example.daggerpractice.data.persistance.model.User
import com.example.daggerpractice.data.persistance.repository.database.dao.ImageDao
import com.example.daggerpractice.data.persistance.repository.database.dao.TextDao
import com.example.daggerpractice.data.persistance.repository.database.dao.UserDao

@Database(version = 2, exportSchema = false, entities = [User::class, Image::class, Text::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun imageDao(): ImageDao

    abstract fun textDao(): TextDao

    companion object {

        private const val NAME = "app_database.db"

        @Synchronized
        fun build(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, NAME)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {

                    }

                    override fun onOpen(db: SupportSQLiteDatabase) {}
                })
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }
    }
}