package com.example.daggerpractice.di.modules

import com.example.daggerpractice.data.web.RandomTextApiInterface
import com.example.daggerpractice.data.web.TheCatApiInterface
import com.example.daggerpractice.di.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class RetrofitModule {

    @Module
    companion object {
        private val CATS_BASE_URL = "https://api.thecatapi.com/v1/images/search/"
        private val TEXT_BASE_URL = "randomtext.me"

        @JvmStatic
        @AppScope
        @Provides
        fun catsApi(@Named("Cats") retrofit: Retrofit): TheCatApiInterface {
            return retrofit.create(TheCatApiInterface::class.java)
        }

        @JvmStatic
        @AppScope
        @Provides
        fun textApi(@Named("Text") retrofit: Retrofit): RandomTextApiInterface {
            return retrofit.create(RandomTextApiInterface::class.java)
        }

        @JvmStatic
        @AppScope
        @Provides
        @Named("Cats")
        fun retrofitForCats(client: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(CATS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        @JvmStatic
        @AppScope
        @Provides
        @Named("Text")
        fun retrofitForText(client: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(TEXT_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
    }
}