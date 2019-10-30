package com.example.daggerpractice.displays.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.daggerpractice.R
import com.example.daggerpractice.data.Repository
import com.example.daggerpractice.data.persistance.model.User
import com.example.daggerpractice.data.pojo_models.image.ImageResponce
import com.example.daggerpractice.data.pojo_models.text.TextResponce
import com.example.daggerpractice.displays.MainActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SplashActivity @Inject constructor() : DaggerAppCompatActivity() {

    @set:Inject
    var viewModelFactory: ViewModelProvider.Factory? = null

    private final val tag: String = "SplashActivity"

    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashViewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel::class.java)

        setObserversToViewModel()
    }

    private fun setObserversToViewModel() {
        splashViewModel.getUsers()

        splashViewModel.users!!.observe(this, Observer {
            Log.d(tag, "set user observer start")
            splashViewModel.downloadDataInFirstLaunch(it)
            Log.d(tag, "set user observer finish")
        })

        Log.d(tag, "start mediator")
        val mediator: MediatorLiveData<String> = MediatorLiveData()

        Log.d(tag, "set first source to mediator")
        mediator.addSource(splashViewModel.images!!) {
            Log.d(tag, "set image observer start")
            splashViewModel.handleAndSaveData(imagesResponse = it)
            Log.d(tag, "set image observer finish")
        }

        Log.d(tag, "set second source to mediator")
        mediator.addSource(splashViewModel.text!!) {
            Log.d(tag, "set text observer start")
            splashViewModel.handleAndSaveData(textResponse = it)
            Log.d(tag, "set text observer finish")
        }

        mediator.observe(this, Observer {
            splashViewModel.handleAndSaveData()
        })
    }

    fun startMainActivity() {
        Log.d(tag, "start MainActivity")
        startActivity(Intent(this, MainActivity::class.java))
    }

//    private fun setUsersObserver() {
//        repository!!.users.observe(this, Observer {
//            users.addAll(it)
//        })
//    }
//
//    private fun downloadDataAndFillDatabase() {
//        downloadImagesAndText()
//
//        val textList = getTextList()
//
//        while (textList.iterator().hasNext() && images.iterator().hasNext()) {
//            val text = textList.iterator().next()
//            val image = images.iterator().next()
//
//            val user = User(image.id!!)
//            user.url = image.url
//            val title = text.split(" ")[0] + text.split(" ")[1]
//            user.title = title
//            user.text = text.substring(title.length)
//
//            repository!!.insertNewUser(user)
//        }
//    }
//
//    private fun downloadImagesAndText() {
//        repository!!.catsImage.observe(this, Observer {
//            images.addAll(it)
//        })
//
//        repository!!.textResponce.observe(this, Observer {
//            text = it
//        })
//    }
//
//    private fun getTextList() : ArrayList<String> {
//        val list = ArrayList<String>()
//
//        var count = text!!.amount!!
//        var textOut = text!!.textOut!!
//
//        while (count != 0) {
//            val string = textOut.substring(textOut.indexOf("<li>"), textOut.indexOf("<\\/li>"))
//            list.add(string)
//            textOut = textOut.substring(string.length)
//            count--
//        }
//
//        return list
//    }
}
