package com.example.daggerpractice.di.modules

import com.example.daggerpractice.DaggerApp
import com.example.daggerpractice.data.Repository
import com.example.daggerpractice.data.client.RandomTextApiInterface
import com.example.daggerpractice.data.client.TheCatApiInterface
import com.example.daggerpractice.data.persistance.repository.Repositories
import com.example.daggerpractice.data.persistance.repository.database.DatabaseRepositoryImpl
import com.example.daggerpractice.di.AppScope
import dagger.Module
import dagger.Provides

@Module(includes = [OkHttpClientModule::class, RetrofitModule::class, ViewModelModule::class, WorkerBindingModule::class])
class DaggerAppModule {

    @Provides
    @AppScope
    fun provideDatabase(app: DaggerApp) = (Repositories.getDatabase(app) as DatabaseRepositoryImpl)

    @Provides
    @AppScope
    fun provideContext(app: DaggerApp) = app.applicationContext

    @Provides
    @AppScope
    fun provideRepository(
        randomTextApi: RandomTextApiInterface,
        theCatApi: TheCatApiInterface,
        database: DatabaseRepositoryImpl
    ) = Repository(randomTextApi, theCatApi, database)
}