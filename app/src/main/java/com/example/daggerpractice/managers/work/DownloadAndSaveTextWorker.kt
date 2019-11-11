package com.example.daggerpractice.managers.work

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.daggerpractice.data.Repository
import com.example.daggerpractice.data.persistance.model.Text
import com.example.daggerpractice.managers.factory.ChildWorkerFactory
import javax.inject.Inject

class DownloadAndSaveTextWorker(
    context: Context,
    workerParams: WorkerParameters,
    private val repository: Repository) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
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
        return Result.success()
    }

    private fun saveRandomText(text: String) {
        repository.insertNewText(Text(text))
    }

    class Factory @Inject constructor(private val repository: Repository): ChildWorkerFactory {
        override fun create(context: Context, params: WorkerParameters): ListenableWorker {
            return DownloadAndSaveTextWorker(context, params, repository)
        }
    }
}
