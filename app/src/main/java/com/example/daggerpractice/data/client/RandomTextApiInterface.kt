package com.example.daggerpractice.data.client

import com.example.daggerpractice.data.pojo_models.text.TextResponce
import retrofit2.http.GET

interface RandomTextApiInterface {

    @GET("api/lorem/ul-5/5-15/")
    suspend fun getRandomText(): TextResponce
}