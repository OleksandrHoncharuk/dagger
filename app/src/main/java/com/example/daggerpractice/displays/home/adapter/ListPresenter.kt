package com.example.daggerpractice.displays.home.adapter

import java.util.ArrayList

class ListPresenter(private var items: ArrayList<ItemData>) {

    val rowsCount: Int
        get() = items.size

    fun onBindRowViewAtPosition(rowView: RowView, position: Int) {
        val item = items[position]
        rowView.setImage(item.imageUrl!!)
        rowView.setFirstLine(item.firstLine!!)
        rowView.setSecondLine(item.secondLine!!)
    }

    operator fun get(position: Int): ItemData {
        return items[position]
    }

    internal fun refreshSearch(refreshList: ArrayList<ItemData>) {
        this.items = ArrayList(refreshList)
    }
}