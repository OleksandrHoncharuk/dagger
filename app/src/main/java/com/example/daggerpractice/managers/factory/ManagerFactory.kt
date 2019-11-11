package com.example.daggerpractice.managers.factory

import android.content.Context

import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.daggerpractice.di.AppScope
import javax.inject.Inject
import javax.inject.Provider

@AppScope
class ManagerFactory @Inject constructor(private val workerFactories: Map<Class<out ListenableWorker>, @JvmSuppressWildcards Provider<ChildWorkerFactory>>) : WorkerFactory() {

    override fun createWorker(context: Context, workerClassName: String, workerParameters: WorkerParameters): ListenableWorker? {
        val foundEntry = workerFactories.entries.find { Class.forName(workerClassName).isAssignableFrom(it.key) }
        val factoryProvider = foundEntry?.value ?: throw IllegalArgumentException("unknown worker class name: $workerClassName")
        return factoryProvider.get().create(context, workerParameters)
    }
}