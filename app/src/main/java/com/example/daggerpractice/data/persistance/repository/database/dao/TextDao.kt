package com.example.daggerpractice.data.persistance.repository.database.dao

import androidx.room.*
import com.example.daggerpractice.data.persistance.model.Text

@Dao
abstract class TextDao {

    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    abstract fun insert(text: Text)

    @Delete
    abstract fun delete(text: Text)

    @Update(onConflict = OnConflictStrategy.ROLLBACK)
    abstract fun update(text: Text)

    @Query("DELETE FROM text")
    abstract fun deleteAll(): Int

    @Query("SELECT * FROM text")
    abstract fun getAll(): List<Text>

    @Query("SELECT * FROM text WHERE id=:id")
    abstract fun findById(id: String): Text

    @Query("SELECT * FROM text WHERE text=:text")
    abstract fun findByTitle(text: String): Text
}