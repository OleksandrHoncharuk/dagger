package com.example.daggerpractice.web

import com.example.daggerpractice.pojo_models.text.TextResponce
import retrofit2.http.GET

interface RandomTextApiInterface {

    @GET("api/lorem/ul-5/5-15/")
    fun getRandomText(): TextResponce
}