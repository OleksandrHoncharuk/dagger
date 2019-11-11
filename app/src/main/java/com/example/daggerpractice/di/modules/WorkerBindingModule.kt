package com.example.daggerpractice.di.modules

import androidx.work.WorkerFactory
import com.example.daggerpractice.di.WorkerKey
import com.example.daggerpractice.managers.factory.ChildWorkerFactory
import com.example.daggerpractice.managers.factory.ManagerFactory
import com.example.daggerpractice.managers.work.DatabaseSizeCheckWorker
import com.example.daggerpractice.managers.work.DownloadAndSaveImagesWorker
import com.example.daggerpractice.managers.work.DownloadAndSaveTextWorker
import com.example.daggerpractice.managers.work.FillDatabaseWithNewUsersWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class WorkerBindingModule {

    @Binds
    @IntoMap
    @WorkerKey(DatabaseSizeCheckWorker::class)
    abstract fun bindDatabaseSizeCheckWorker(factory: DatabaseSizeCheckWorker.Factory): ChildWorkerFactory

    @Binds
    @IntoMap
    @WorkerKey(DownloadAndSaveImagesWorker::class)
    abstract fun bindDownloadAndSaveImagesWorker(factory: DownloadAndSaveImagesWorker.Factory): ChildWorkerFactory

    @Binds
    @IntoMap
    @WorkerKey(DownloadAndSaveTextWorker::class)
    abstract fun bindDownloadAndSaveTextWorker(factory: DownloadAndSaveTextWorker.Factory): ChildWorkerFactory

    @Binds
    @IntoMap
    @WorkerKey(FillDatabaseWithNewUsersWorker::class)
    abstract fun bindFillDatabaseWithNewUsersWorker(factory: FillDatabaseWithNewUsersWorker.Factory): ChildWorkerFactory

    @Binds
    abstract fun bindsManagerFactory(managerFactory: ManagerFactory): WorkerFactory
}