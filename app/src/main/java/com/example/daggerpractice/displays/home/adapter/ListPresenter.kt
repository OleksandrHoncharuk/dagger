package com.example.daggerpractice.displays.home.adapter

import android.util.Log
import java.util.ArrayList

class ListPresenter(private var items: ArrayList<ItemData>) {

    private val TAG = ListPresenter::class.java.simpleName

    val rowsCount: Int
        get() = items.size

    fun onBindRowViewAtPosition(rowView: RowView, position: Int) {
        Log.d(TAG, "onBindRowViewAtPosition item on position = $position")
        val item = items[position]
        rowView.setImage(item.imageUrl!!)
        rowView.setFirstLine(item.firstLine!!)
        rowView.setSecondLine(item.secondLine!!)
    }

    operator fun get(position: Int): ItemData {
        Log.d(TAG, "get item on position = $position")
        return items[position]
    }

    fun removeItemAt(position: Int) {
        Log.d(TAG, "remove item on position = $position")
        items.removeAt(position)
    }

    internal fun refresh(refreshList: ArrayList<ItemData>) {
        Log.d(TAG, "refresh items list")
        this.items = ArrayList(refreshList)
    }
}