package com.example.daggerpractice.managers.work

import android.content.Context
import android.util.Log
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.daggerpractice.data.Repository
import com.example.daggerpractice.data.persistance.model.Image
import com.example.daggerpractice.data.persistance.model.User
import com.example.daggerpractice.di.AppScope
import com.example.daggerpractice.managers.factory.ChildWorkerFactory
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class FillDatabaseWithNewUsersWorker(context: Context, workerParams: WorkerParameters, private val repository: Repository): Worker(context, workerParams) {

    private val TAG = FillDatabaseWithNewUsersWorker::class.java.simpleName

    override fun doWork(): Result {
        return try {
            runBlocking {
                val imagesList = async { repository.getAllImages() }
                val textList = async { repository.getAllText() }

                val imagesIterator = imagesList.await().iterator()
                val textIterator = textList.await().iterator()

                while (imagesIterator.hasNext() && textIterator.hasNext()) {
                    repository.insertNewUser(imagesIterator.next(), textIterator.next().text!!)
                }
            }

            return Result.success()
        } catch (error: Throwable) {
            Result.failure()
        }

    }

    @AppScope
    class Factory @Inject constructor(private val repository: Repository): ChildWorkerFactory {
        override fun create(context: Context, params: WorkerParameters): ListenableWorker {
            return FillDatabaseWithNewUsersWorker(context, params, repository)
        }
    }
}