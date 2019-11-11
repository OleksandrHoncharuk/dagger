package com.example.daggerpractice.data.persistance.repository.database.dao

import androidx.room.*
import com.example.daggerpractice.data.persistance.model.Image

@Dao
abstract class ImageDao {

    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    abstract fun insert(image: Image)

    @Delete
    abstract fun delete(image: Image)

    @Update(onConflict = OnConflictStrategy.ROLLBACK)
    abstract fun update(image: Image)

    @Query("DELETE FROM image")
    abstract fun deleteAll(): Int

    @Query("SELECT * FROM image")
    abstract fun getAll(): List<Image>

    @Query("SELECT * FROM image WHERE id=:id")
    abstract fun findById(id: String): Image

    @Query("SELECT * FROM image WHERE url=:url")
    abstract fun findByTitle(url: String): Image
}