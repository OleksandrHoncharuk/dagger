package com.example.daggerpractice.managers.work

import android.content.Context
import android.util.Log
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.daggerpractice.data.Repository
import com.example.daggerpractice.data.persistance.model.Image
import com.example.daggerpractice.data.pojo_models.image.ImageResponce
import com.example.daggerpractice.managers.factory.ChildWorkerFactory
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DownloadAndSaveImagesWorker(
    context: Context,
    workerParams: WorkerParameters,
    private val repository: Repository)
    : Worker(context, workerParams){

    override fun doWork(): Result {
        return try {
            runBlocking {
                val imagesResponse = repository.getCatsImage()
                imagesResponse.forEach { response ->
                    insertImage(response)
                }
            }

            Result.success()
        } catch (error: Throwable) {
            Log.e("DownloadAndSaveImages", error.message!!)
            Result.failure()
        }
    }

    private fun insertImage(response: ImageResponce) {
        Log.d("DownloadAndSaveImages", "new image added")
        repository.insertNewImage(Image(response.id!!, response.url!!))
    }

    class Factory @Inject constructor(private val repository: Repository): ChildWorkerFactory {
        override fun create(context: Context, params: WorkerParameters): ListenableWorker {
            return DownloadAndSaveImagesWorker(context, params, repository)
        }
    }
}