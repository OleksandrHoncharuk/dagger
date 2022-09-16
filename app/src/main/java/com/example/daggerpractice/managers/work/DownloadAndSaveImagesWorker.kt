package com.example.daggerpractice.managers.work

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.daggerpractice.data.Repository
import com.example.daggerpractice.data.persistance.model.Image
import com.example.daggerpractice.data.pojo_models.image.ImageResponce
import com.example.daggerpractice.di.AppScope
import com.example.daggerpractice.managers.factory.ChildWorkerFactory
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DownloadAndSaveImagesWorker(context: Context, workerParams: WorkerParameters, private val repository: Repository): CoroutineWorker(context, workerParams){

    private val TAG = DownloadAndSaveImagesWorker::class.java.simpleName

    override suspend fun doWork(): Result {
        return try {
            coroutineScope {
                val imagesResponse = repository.getCatsImage()
                imagesResponse.forEach { response ->
                    repository.insertNewImage(response)
                }
            }

            Result.success()
        } catch (error: Throwable) {
            Log.e(TAG, error.message!!)
            Result.failure()
        }
    }

    @AppScope
    class Factory @Inject constructor(private val repository: Repository): ChildWorkerFactory {
        override fun create(context: Context, params: WorkerParameters): ListenableWorker {
            return DownloadAndSaveImagesWorker(context, params, repository)
        }
    }
}