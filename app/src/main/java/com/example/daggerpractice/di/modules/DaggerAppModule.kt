package com.example.daggerpractice.di.modules

import android.content.Context
import com.example.daggerpractice.DaggerApp
import com.example.daggerpractice.data.Repository
import com.example.daggerpractice.data.client.RandomTextApiInterface
import com.example.daggerpractice.data.client.TheCatApiInterface
import com.example.daggerpractice.data.persistance.repository.Repositories
import com.example.daggerpractice.data.persistance.repository.database.DatabaseRepositoryImpl
import com.example.daggerpractice.data.persistance.repository.database.dao.UserDao
import com.example.daggerpractice.di.AppScope
import dagger.Module
import dagger.Provides

@Module(includes = [OkHttpClientModule::class, RetrofitModule::class, ViewModelModule::class])
class DaggerAppModule {

    @Provides
    @AppScope
    fun provideDao(app: DaggerApp): UserDao {
        return (Repositories.getDatabase(app) as DatabaseRepositoryImpl).userDao()
    }

    @Provides
    @AppScope
    fun provideContext(app: DaggerApp): Context {
        return app.applicationContext
    }

    @Provides
    @AppScope
    fun provideRepository(
        randomTextApi: RandomTextApiInterface,
        theCatApi: TheCatApiInterface,
        userDao: UserDao
    ): Repository {
        return Repository(randomTextApi, theCatApi, userDao)
    }
}