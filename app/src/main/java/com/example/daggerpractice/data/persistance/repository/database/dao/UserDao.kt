package com.example.daggerpractice.data.persistance.repository.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.daggerpractice.data.persistance.model.User

@Dao
abstract class UserDao {

    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    abstract fun insert(user: User)

    @Delete
    abstract fun delete(user: User)

    @Update(onConflict = OnConflictStrategy.ROLLBACK)
    abstract fun update(user: User)

    @Query("DELETE FROM user")
    abstract fun deleteAll(): Int

    @Query("SELECT * FROM user")
    abstract fun getAll(): List<User>

    @Query("SELECT * FROM user")
    abstract fun getUsersLiveData(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE id=:id")
    abstract fun findById(id: String): User

    @Query("SELECT * FROM user WHERE title=:title")
    abstract fun findByTitle(title: String): User
}