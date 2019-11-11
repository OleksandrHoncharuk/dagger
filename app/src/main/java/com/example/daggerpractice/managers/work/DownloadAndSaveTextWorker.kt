package com.example.daggerpractice.managers.work

import android.content.Context
import android.util.Log
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.daggerpractice.data.Repository
import com.example.daggerpractice.data.persistance.model.Text
import com.example.daggerpractice.di.AppScope
import com.example.daggerpractice.managers.factory.ChildWorkerFactory
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AppScope
class DownloadAndSaveTextWorker(
    context: Context,
    workerParams: WorkerParameters,
    private val repository: Repository
) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        return try {
            runBlocking {
                repository.getRandomText().textOut!!
                    .substring(10)
                    .replace("</ul>", "")
                    .replace("\\r", "")
                    .replace("</li>", "")
                    .replace("<\\/li>", "")
                    .split("<li>")
                    .forEach {
                        saveRandomText(it)
                    }
            }

            Result.success()

        } catch (error: Throwable) {
            Result.failure()
        }
    }

    private fun saveRandomText(text: String) {
        Log.d("DownloadAndSaveText", "new text added")
        repository.insertNewText(Text(text))
    }

    @AppScope
    class Factory @Inject constructor(private val repository: Repository) : ChildWorkerFactory {
        override fun create(context: Context, params: WorkerParameters): ListenableWorker {
            return DownloadAndSaveTextWorker(context, params, repository)
        }
    }
}
