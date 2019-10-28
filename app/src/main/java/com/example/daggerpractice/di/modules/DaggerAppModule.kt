package com.example.daggerpractice.di.modules

import android.app.Application
import com.example.daggerpractice.DaggerApp
import com.example.daggerpractice.data.persistance.repository.Repositories
import com.example.daggerpractice.data.persistance.repository.database.DatabaseRepositoryImpl
import com.example.daggerpractice.data.persistance.repository.database.dao.UserDao
import com.example.daggerpractice.di.AppScope
import dagger.Module
import dagger.Provides

@Module(includes = [OkHttpClientModule::class, RetrofitModule::class])
class DaggerAppModule(private val app: DaggerApp) {


    @Provides
    @AppScope
    fun provideApplication(): Application {
        return app
    }

    @Provides
    @AppScope
    fun provideDao(app: Application): UserDao {
        return (Repositories.getDatabase(app) as DatabaseRepositoryImpl).userDao()
    }
}