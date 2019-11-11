package com.example.daggerpractice

import androidx.work.*
import com.example.daggerpractice.di.components.DaggerAppComponent
import com.example.daggerpractice.managers.factory.ManagerFactory
import com.example.daggerpractice.managers.work.DatabaseSizeCheckWorker
import com.example.daggerpractice.managers.work.DownloadAndSaveImagesWorker
import com.example.daggerpractice.managers.work.DownloadAndSaveTextWorker
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class DaggerApp: DaggerApplication() {

    @Inject lateinit var managerFactory: ManagerFactory

    override fun applicationInjector(): AndroidInjector<out DaggerApp> {
        return DaggerAppComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()

        WorkManager.getInstance(this)
            .beginWith(databaseCheck())
            .then(listOf(downloadImages(), downloadText()))
            .then(fillDatabase())
            .enqueue()
    }

    private fun databaseCheck() = OneTimeWorkRequestBuilder<DatabaseSizeCheckWorker>().build()

    private fun downloadImages() = OneTimeWorkRequestBuilder<DownloadAndSaveImagesWorker>()
//        .setConstraints(networkConstraints())
//        .setConstraints(storageConstraints())
        .build()

    private fun downloadText() = OneTimeWorkRequestBuilder<DownloadAndSaveTextWorker>()
//        .setConstraints(networkConstraints())
//        .setConstraints(storageConstraints())
        .build()

    private fun fillDatabase() = OneTimeWorkRequestBuilder<DownloadAndSaveTextWorker>()
//        .setConstraints(storageConstraints())
        .build()

    private fun networkConstraints() = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    private fun storageConstraints() = Constraints.Builder()
        .setRequiresStorageNotLow(true)
        .build()
}