package com.example.daggerpractice.data.persistance.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "text")
class Text {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var text: String? = null

    constructor()

    @Ignore
    constructor(text: String) {
        this.text = text
    }

    @Ignore
    constructor(id: Int, text: String) {
        this.id = id
        this.text = text
    }
}