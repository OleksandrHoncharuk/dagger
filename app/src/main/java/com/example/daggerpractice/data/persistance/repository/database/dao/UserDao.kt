package com.example.daggerpractice.data.persistance.repository.database.dao

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
    abstract suspend fun deleteAll(): Int

    @Query("SELECT * FROM user")
    abstract suspend fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE id=:id")
    abstract fun findById(id: String): User

    @Query("SELECT * FROM user WHERE title=:title")
    abstract fun findByTitle(title: String): User
}