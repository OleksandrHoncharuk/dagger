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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AppScope
class Repository @Inject constructor(
    private val randomTextApi: RandomTextApiInterface, private val theCatApi: TheCatApiInterface,
    private val database: DatabaseRepositoryImpl
) {

    private val TAG = Repository::class.java.simpleName

    /**
     * Network API
     */
    suspend fun getRandomText(): TextResponce {
        Log.d(TAG, "start download text")
        return withContext(Dispatchers.IO) {
            randomTextApi.getRandomText()
        }
    }

    suspend fun getCatsImage(): List<ImageResponce> {
        Log.d(TAG, "start download image")
        return withContext(Dispatchers.IO) {
            theCatApi.getCatsImage()
        }
    }

    /**
     * UserDao methods
     */
    suspend fun getAllUsers(): List<User> {
        Log.d(TAG, "start get user from database")
        return withContext(Dispatchers.IO) {
            database.userDao().getAll()
        }
    }

    fun getUsersLiveData(): LiveData<List<User>> {
        Log.d(TAG, "start get user from database")
        return database.userDao().getUsersLiveData()

    }

    suspend fun insertNewUser(user: User) {
        Log.d(TAG, "add new user")
        withContext(Dispatchers.IO) {
            database.userDao().insert(user)
        }
    }

    suspend fun insertNewUser(image: Image, text: String) {
        val user = User(image.id)
        user.url = image.url

        val title = text.split(" ")[0] + text.split(" ")[1]
        user.title = title
        user.text = text.substring(title.length)

        insertNewUser(user)
    }

    suspend fun deleteUser(user: User) {
        withContext(Dispatchers.IO) {
            database.userDao().delete(user)
        }
    }

    suspend fun deleteUserById(id: String) {
        withContext(Dispatchers.IO) {
            val user = findById(id)
            deleteUser(user)
        }
    }

    suspend fun deleteUserByTitle(title: String) {
        withContext(Dispatchers.IO) {
            val user = findByTitle(title)
            deleteUser(user)
        }
    }

    suspend fun findByTitle(title: String) : User {
        return withContext(Dispatchers.IO) {
            database.userDao().findByTitle(title)
        }
    }

    suspend fun findById(id: String) : User {
        return withContext(Dispatchers.IO) {
            database.userDao().findById(id)
        }
    }

    suspend fun deleteAllUsers(): Int {
        return withContext(Dispatchers.IO) {
            database.userDao().deleteAll()
        }
    }

    /**
     * ImageDao methods
     */

    suspend fun insertNewImage(image: Image) {
        Log.d(TAG, "new image added")
        withContext(Dispatchers.IO) {
            database.imageDao().insert(image)
        }
    }

    suspend fun insertNewImage(response: ImageResponce) {
        insertNewImage(Image(response.id!!, response.url!!))
    }

    suspend fun deleteImage(image: Image) {
        withContext(Dispatchers.IO) {
            database.imageDao().delete(image)
        }
    }

    suspend fun getAllImages(): List<Image>  {
        return withContext(Dispatchers.IO) {
            database.imageDao().getAll()
        }
    }


    /**
     * TextDao methods
     */

    suspend fun insertNewText(text: Text) {
        Log.d(TAG, "new text added")
        withContext(Dispatchers.IO) {
            database.textDao().insert(text)
        }
    }

    suspend fun insertNewText(textResponce: TextResponce) {
        textResponce.textOut!!
            .substring(10)
            .replace("</ul>", "")
            .replace("\\r", "")
            .replace("</li>", "")
            .replace("<\\/li>", "")
            .split("<li>")
            .forEach {
                insertNewText(Text(it))
            }
    }

    suspend fun deleteText(text: Text) {
        withContext(Dispatchers.IO) {
            database.textDao().delete(text)
        }
    }

    suspend fun getAllText() : List<Text> {
        return withContext(Dispatchers.IO) {
            database.textDao().getAll()
        }
    }

}