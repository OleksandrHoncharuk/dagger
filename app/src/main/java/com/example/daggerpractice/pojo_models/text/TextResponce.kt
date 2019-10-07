package com.example.daggerpractice.pojo_models.text

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class TextResponce {

    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("amount")
    @Expose
    var amount: Int? = null
    @SerializedName("format")
    @Expose
    var format: String? = null
    @SerializedName("number")
    @Expose
    var number: String? = null
    @SerializedName("number_max")
    @Expose
    var numberMax: String? = null
    @SerializedName("time")
    @Expose
    var time: String? = null
    @SerializedName("text_out")
    @Expose
    var textOut: String? = null

}