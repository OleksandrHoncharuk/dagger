package com.example.daggerpractice.pojo_models.image

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Category {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null

}