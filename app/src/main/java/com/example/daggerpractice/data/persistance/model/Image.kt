package com.example.daggerpractice.data.persistance.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "image")
class Image {

    @PrimaryKey
    lateinit var id: String
    var url: String? = null

    constructor()

    @Ignore
    constructor(id: String) {
        this.id = id
    }

    @Ignore
    constructor(id: String, url: String) {
        this.id = id
        this.url = url
    }
}