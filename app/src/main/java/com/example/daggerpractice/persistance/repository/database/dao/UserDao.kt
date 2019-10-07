package com.example.daggerpractice.persistance.repository.database.dao

import androidx.room.*
import com.example.daggerpractice.persistance.model.User

@Dao
abstract class UserDao {
    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    abstract fun insert(user: User)

    @Delete
    abstract fun delete(user: User)

    @Update(onConflict = OnConflictStrategy.ROLLBACK)
    abstract fun update(user: User)

    @Query("DELETE FROM user")
    abstract fun deleteAll()

    @Query("SELECT * FROM user WHERE id=:id")
    abstract fun findById(id: String): User

    @Query("SELECT * FROM user WHERE title=:title")
    abstract fun findByTitle(title: String): User
}