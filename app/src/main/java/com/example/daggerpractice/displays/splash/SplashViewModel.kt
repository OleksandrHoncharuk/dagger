package com.example.daggerpractice.displays.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daggerpractice.data.Repository
import com.example.daggerpractice.data.persistance.model.User
import com.example.daggerpractice.data.pojo_models.image.ImageResponce
import com.example.daggerpractice.data.pojo_models.text.TextResponce
import javax.inject.Inject

class SplashViewModel @Inject
constructor(private val view: SplashActivity, private val repository: Repository): ViewModel() {

    private val imagesUrlList: ArrayList<String>
    private val textList: ArrayList<String>

    private var _images: LiveData<List<ImageResponce>>? = null
    var images: LiveData<List<ImageResponce>>? = null

    private var _text: LiveData<TextResponce>? = null
    var text: LiveData<TextResponce>? = null

    private var _users: LiveData<List<User>>? = null
    var users: LiveData<List<User>>? = null

    init {
        imagesUrlList = ArrayList()
        textList = ArrayList()
    }

    fun getUsers() {
        Log.d("SplashViewModel", "get users")
        users = repository.users
    }

    fun downloadDataInFirstLaunch(users: List<User>?) {
        Log.d("SplashViewModel", users.isNullOrEmpty().toString())
        if (users.isNullOrEmpty()) {
            Log.d("SplashViewModel", "set value to livedata")
            images = repository.catsImage
            text = repository.textResponce
        }
    }

    fun handleAndSaveData(imagesResponse: List<ImageResponce>? = null, textResponse: TextResponce? = null) {
        if (imagesResponse.isNullOrEmpty().not()) {
            imagesResponse!!.forEach { image ->
                imagesUrlList.add(image.id!! + " " + image.url)
            }
        }

        else if (textResponse != null) {
           addTextToTextList(textResponse)
        }

        if (imagesUrlList.isEmpty().not() && textList.isEmpty().not()) {
            if (imagesUrlList.size == textList.size) {
                val imagesIterator = imagesUrlList.iterator()
                val textIterator = textList.iterator()

                while (imagesIterator.hasNext() && textIterator.hasNext()) {
                    addUser(imagesIterator.next(), textIterator.next())
                }

                view.startMainActivity()
            }
        }
    }

    private fun addTextToTextList(textResponse: TextResponce) {
        var count = textResponse.amount!!
        var textOut = textResponse.textOut!!

        while (count != 0) {
            val string = textOut.substring(textOut.indexOf("<li>"), textOut.indexOf("<\\/li>"))
            textList.add(string)
            textOut = textOut.substring(string.length)
            count--
        }
    }

    private fun addUser(image: String, text: String) {
        val user = User(image.split(" ")[0])
        user.url = image.split(" ")[1]

        val title = text.split(" ")[0] + text.split(" ")[1]
        user.title = title
        user.text = text.substring(title.length)

        repository.insertNewUser(user)
    }
}