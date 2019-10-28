package com.example.daggerpractice.data.persistance.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class User {

    @PrimaryKey
    lateinit var id: String
    var title: String? = null
    var text: String? = null
    var url: String? = null

    constructor()

    @Ignore
    constructor(id: String) {
        this.id = id
    }

    @Ignore
    constructor(id: String, title: String?, text: String?, url: String?) {
        this.id = id
        this.title = title
        this.text = text
        this.url = url
    }
}