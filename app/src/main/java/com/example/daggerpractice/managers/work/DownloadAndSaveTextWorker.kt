package com.example.daggerpractice.managers.work

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.daggerpractice.data.Repository
import com.example.daggerpractice.data.persistance.model.Text
import com.example.daggerpractice.di.AppScope
import com.example.daggerpractice.managers.factory.ChildWorkerFactory
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DownloadAndSaveTextWorker(context: Context, workerParams: WorkerParameters, private val repository: Repository): CoroutineWorker(context, workerParams) {

    private val TAG = DownloadAndSaveTextWorker::class.java.simpleName

    override suspend fun doWork(): Result {
        return try {
            coroutineScope {
                repository.insertNewText(repository.getRandomText())
            }

            Result.success()

        } catch (error: Throwable) {
            Result.failure()
        }
    }

    @AppScope
    class Factory @Inject constructor(private val repository: Repository) : ChildWorkerFactory {
        override fun create(context: Context, params: WorkerParameters): ListenableWorker {
            return DownloadAndSaveTextWorker(context, params, repository)
        }
    }
}
