package com.example.daggerpractice.data.pojo_models.image

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ImageResponce {

    @SerializedName("breeds")
    @Expose
    var breeds: List<Breed>? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("width")
    @Expose
    var width: Int? = null
    @SerializedName("height")
    @Expose
    var height: Int? = null
    @SerializedName("categories")
    @Expose
    var categories: List<Category>? = null

}