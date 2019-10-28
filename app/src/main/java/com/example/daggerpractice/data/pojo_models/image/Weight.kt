package com.example.daggerpractice.data.pojo_models.image

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Weight {

    @SerializedName("imperial")
    @Expose
    var imperial: String? = null
    @SerializedName("metric")
    @Expose
    var metric: String? = null

}