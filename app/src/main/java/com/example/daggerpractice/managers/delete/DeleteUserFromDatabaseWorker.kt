package com.example.daggerpractice.managers.delete

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.daggerpractice.data.Repository
import com.example.daggerpractice.di.AppScope
import com.example.daggerpractice.managers.factory.ChildWorkerFactory
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DeleteUserFromDatabaseWorker(context: Context, workerParams: WorkerParameters, private val repository: Repository, private val id: String): CoroutineWorker(context, workerParams) {

    private val TAG = DeleteUserFromDatabaseWorker::class.java.simpleName

    override suspend fun doWork(): Result {
        return try {
            coroutineScope {
                repository.deleteUserById(id)
            }
            Result.success()
        } catch (error: Throwable) {
            Result.failure()
        }
    }

    @AppScope
    class Factory @Inject constructor(private val repository: Repository, private val id: String) : ChildWorkerFactory {
        override fun create(context: Context, params: WorkerParameters): ListenableWorker {
            return DeleteUserFromDatabaseWorker(context, params, repository, id)
        }
    }
}