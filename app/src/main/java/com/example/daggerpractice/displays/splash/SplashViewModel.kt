package com.example.daggerpractice.displays.splash

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daggerpractice.data.Repository
import com.example.daggerpractice.data.persistance.model.User
import com.example.daggerpractice.data.pojo_models.image.ImageResponce
import com.example.daggerpractice.data.pojo_models.text.TextResponce
import com.example.daggerpractice.displays.MainActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject
constructor(private val repository: Repository): ViewModel() {

    private val imagesUrlList: ArrayList<String> = ArrayList()
    private val textList: ArrayList<String> = ArrayList()

    var isDatabaseFull = false
    private var isImagesDownloaded = false
    private var isTextDownloaded = false

    private val _images = MutableLiveData<List<ImageResponce>>()
    val images: LiveData<List<ImageResponce>>
        get() = _images

    private val _text = MutableLiveData<TextResponce>()
    val text: LiveData<TextResponce> get() = _text

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    init {
        viewModelScope.launch {
            _users.value = repository.getAllUsers()

            if (_users.value.isNullOrEmpty()) {
                _images.value = repository.getCatsImage()
                _text.value = repository.getRandomText()
            }
        }
    }

    fun handleAndSaveImage(imagesResponse: List<ImageResponce>) {
        imagesResponse.forEach { image ->
            imagesUrlList.add(image.id!! + " " + image.url)
        }

        isImagesDownloaded = true
    }

    fun handleAndSaveRandomText(textResponse: TextResponce) {
        textResponse.textOut!!
            .substring(10)
            .replace("</ul>", "")
            .replace("\\r", "")
            .replace("</li>", "")
            .replace("<\\/li>", "")
            .split("<li>")
            .forEach {
                textList.add(it)
            }

        isTextDownloaded = true
    }

    fun isDownloadComplete(): Boolean {
        return isImagesDownloaded && isTextDownloaded
    }

    fun handleAndSaveData() {
        if (imagesUrlList.isEmpty().not() && textList.isEmpty().not()) {
            if (imagesUrlList.size == textList.size) {
                val imagesIterator = imagesUrlList.iterator()
                val textIterator = textList.iterator()

                while (imagesIterator.hasNext() && textIterator.hasNext()) {
                    addUser(imagesIterator.next(), textIterator.next())
                }
            }
        }
    }

    private fun addUser(image: String, text: String) {
        val user = User(image.split(" ")[0])
        user.url = image.split(" ")[1]

        val title = text.split(" ")[0] + text.split(" ")[1]
        user.title = title
        user.text = text.substring(title.length)

        Log.d("User", user.url + user.text + user.title)
        repository.insertNewUser(user)
    }
}