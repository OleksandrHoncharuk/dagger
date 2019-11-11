package com.example.daggerpractice.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.daggerpractice.data.client.RandomTextApiInterface
import com.example.daggerpractice.data.client.TheCatApiInterface
import com.example.daggerpractice.data.persistance.model.Image
import com.example.daggerpractice.data.persistance.model.Text
import com.example.daggerpractice.data.persistance.model.User
import com.example.daggerpractice.data.persistance.repository.database.DatabaseRepositoryImpl
import com.example.daggerpractice.data.pojo_models.image.ImageResponce
import com.example.daggerpractice.data.pojo_models.text.TextResponce
import com.example.daggerpractice.di.AppScope
import javax.inject.Inject

@AppScope
class Repository @Inject constructor(
    private val randomTextApi: RandomTextApiInterface, private val theCatApi: TheCatApiInterface,
    private val database: DatabaseRepositoryImpl
) {

//    val textResponce: LiveData<TextResponce> = liveData {
//        Log.d("Repository", "start download text")
//        val data = randomTextApi.getRandomText()
//        Log.d("Repository", "finish download text")
//        emit(data)
//    }
//
//
//    val catsImage: LiveData<List<ImageResponce>> = liveData {
//        Log.d("Repository", "start download image")
//        val data = theCatApi.getCatsImage()
//        Log.d("Repository", "finish download image")
//        emit(data)
//    }
//
//    val users: LiveData<List<User>> = liveData {
//        Log.d("Repository", "start get user from database")
//        val data = database.userDao().getAll()
//        Log.d("Repository", "finish get user from database")
//        emit(data)
//    }

    /**
     * Network API
     */
    suspend fun getRandomText(): TextResponce {
        Log.d("Repository", "start download text")
        return randomTextApi.getRandomText()
    }

    suspend fun getCatsImage(): List<ImageResponce> {
        Log.d("Repository", "start download image")
        return theCatApi.getCatsImage()
    }

    /**
     * UserDao methods
     */
    fun getAllUsers(): List<User> {
        Log.d("Repository", "start get user from database")
        return database.userDao().getAll()
    }

    fun insertNewUser(user: User) {
        Log.d("Repository", "add new user")
        database.userDao().insert(user)
    }

    fun deleteUser(user: User) = database.userDao().delete(user)

    fun findByTitle(title: String) = database.userDao().findByTitle(title)

    fun findById(id: String) = database.userDao().findById(id)

    suspend fun deleteAllUsers() = database.userDao().deleteAll()

    /**
     * ImageDao methods
     */

    fun insertNewImage(image: Image) = database.imageDao().insert(image)

    fun deleteImage(image: Image) = database.imageDao().delete(image)

    //todo make fun suspend
    fun getAllImages() = database.imageDao().getAll()


    /**
     * TextDao methods
     */

    fun insertNewText(text: Text) = database.textDao().insert(text)

    fun deleteText(text: Text) = database.textDao().delete(text)

    //todo make fun suspend
    fun getAllText() = database.textDao().getAll()

}