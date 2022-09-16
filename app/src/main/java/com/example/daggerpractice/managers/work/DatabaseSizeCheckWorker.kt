package com.example.daggerpractice.managers.work

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.daggerpractice.data.Repository
import com.example.daggerpractice.managers.factory.ChildWorkerFactory
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DatabaseSizeCheckWorker(context: Context, workerParams: WorkerParameters, private val repository: Repository)
    : CoroutineWorker(context, workerParams) {

    private val TAG = DatabaseSizeCheckWorker::class.java.simpleName

    override suspend fun doWork(): Result {
        return try {
            coroutineScope {
                val users = repository.getAllUsers()

                if (users.isEmpty()) {
                    Log.d(TAG, "user is empty. Start download")
                    Result.success()
                } else {
                    Log.d(TAG, "user is not empty. Cancel download")
                    Result.failure()
                }
            }
        } catch (error: Throwable) {
            Result.failure()
        }
    }

    class Factory @Inject constructor(private val repository: Repository): ChildWorkerFactory {
        override fun create(context: Context, params: WorkerParameters): ListenableWorker {
            return DatabaseSizeCheckWorker(context, params, repository)
        }
    }
}