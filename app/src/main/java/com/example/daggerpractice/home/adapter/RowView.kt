package com.example.daggerpractice.home.adapter

interface RowView {

    fun setImage(imageUrl: String)

    fun setFirstLine(line: String)

    fun setSecondLine(line: String)
}