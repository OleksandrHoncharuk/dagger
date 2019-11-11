package com.example.daggerpractice.managers.work

import android.content.Context
import android.util.Log
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.daggerpractice.data.Repository
import com.example.daggerpractice.data.persistance.model.Image
import com.example.daggerpractice.data.persistance.model.User
import com.example.daggerpractice.managers.factory.ChildWorkerFactory
import javax.inject.Inject

class FillDatabaseWithNewUsersWorker(context: Context, workerParams: WorkerParameters, private val repository: Repository) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        val imagesList = repository.getAllImages()
        val textList = repository.getAllText()


        val imagesIterator = imagesList.iterator()
        val textIterator = textList.iterator()

        while (imagesIterator.hasNext() && textIterator.hasNext()) {
            addUser(imagesIterator.next(), textIterator.next().text!!)
        }

        Log.d("FillDatabaseWithNew", "New User added")
        return Result.success()
    }

    private fun addUser(image: Image, text: String) {
        val user = User(image.id)
        user.url = image.url

        val title = text.split(" ")[0] + text.split(" ")[1]
        user.title = title
        user.text = text.substring(title.length)

        repository.insertNewUser(user)
    }

    class Factory @Inject constructor(private val repository: Repository): ChildWorkerFactory {
        override fun create(context: Context, params: WorkerParameters): ListenableWorker {
            return FillDatabaseWithNewUsersWorker(context, params, repository)
        }
    }
}