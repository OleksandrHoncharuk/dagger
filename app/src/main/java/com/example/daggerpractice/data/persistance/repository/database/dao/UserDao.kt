package com.example.daggerpractice.data.persistance.repository.database.dao

import androidx.room.*
import com.example.daggerpractice.data.persistance.model.User

@Dao
abstract class UserDao {
    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    abstract suspend fun insert(user: User)

    @Delete
    abstract suspend fun delete(user: User)

    @Update(onConflict = OnConflictStrategy.ROLLBACK)
    abstract suspend fun update(user: User)

    @Query("DELETE FROM user")
    abstract suspend fun deleteAll()

    @Query("SELECT * FROM user WHERE id=:id")
    abstract suspend fun findById(id: String): User

    @Query("SELECT * FROM user WHERE title=:title")
    abstract suspend fun findByTitle(title: String): User
}