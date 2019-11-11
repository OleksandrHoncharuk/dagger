package com.example.daggerpractice.managers.work

import android.content.Context
import android.util.Log
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.daggerpractice.data.Repository
import com.example.daggerpractice.managers.factory.ChildWorkerFactory
import javax.inject.Inject

class DatabaseSizeCheckWorker(context: Context, workerParams: WorkerParameters, private val repository: Repository)
    : Worker(context, workerParams) {

    override fun doWork(): Result {
        val users = repository.getAllUsers()

        return if (users.isEmpty()) {
            Log.d("DatabaseSizeCheckWorker", "user is empty. Start download")
            Result.success()
        }
        else {
            Log.d("DatabaseSizeCheckWorker", "user is not empty. Cancel download")
            Result.failure()
        }
    }

    class Factory @Inject constructor(private val repository: Repository): ChildWorkerFactory {
        override fun create(context: Context, params: WorkerParameters): ListenableWorker {
            return DatabaseSizeCheckWorker(context, params, repository)
        }
    }
}