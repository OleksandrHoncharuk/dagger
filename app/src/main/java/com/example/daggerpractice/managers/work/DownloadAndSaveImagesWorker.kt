package com.example.daggerpractice.managers.work

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.daggerpractice.data.Repository
import com.example.daggerpractice.data.persistance.model.Image
import com.example.daggerpractice.data.pojo_models.image.ImageResponce
import com.example.daggerpractice.managers.factory.ChildWorkerFactory
import javax.inject.Inject

class DownloadAndSaveImagesWorker(
    context: Context,
    workerParams: WorkerParameters,
    private val repository: Repository)
    : Worker(context, workerParams){

    override fun doWork(): Result {
        val imagesResponse = repository.getCatsImage()

        imagesResponse.forEach { response ->
            insertImage(response)
        }
        return Result.success()
    }

    private fun insertImage(response: ImageResponce) {
        repository.insertNewImage(Image(response.id!!, response.url!!))
    }

    class Factory @Inject constructor(private val repository: Repository): ChildWorkerFactory {
        override fun create(context: Context, params: WorkerParameters): ListenableWorker {
            return DownloadAndSaveImagesWorker(context, params, repository)
        }
    }
}