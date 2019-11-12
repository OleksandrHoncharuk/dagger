package com.example.daggerpractice.displays.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.daggerpractice.R
import java.util.ArrayList

class HomeAdapter(private val presenter: ListPresenter) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private val TAG = HomeAdapter::class.java.simpleName

    private var onItemClickedListener: OnItemClickedListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): HomeViewHolder {
        Log.d(TAG, "onCreateViewHolder")
        return HomeViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.recycler_item, null))
    }

    fun setOnItemClickedListener(onItemClickedListener: OnItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener
    }

    fun removeItem(position: Int) {
        val recentlyDeletedItem = presenter[position]
        Log.d(TAG, "Try to remove item on position $position")
        presenter.removeItemAt(position)
        notifyItemRemoved(position)
    }

    override fun onBindViewHolder(viewHolder: HomeViewHolder, i: Int) {
        presenter.onBindRowViewAtPosition(viewHolder, i)
    }

    override fun getItemCount(): Int {
        return presenter.rowsCount
    }

    interface OnItemClickedListener {
        fun onClicked(itemData: ItemData)
    }

    fun refresh(items: ArrayList<ItemData>) {
        presenter.refresh(items)
    }

    inner class HomeViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView), RowView, View.OnClickListener {
        private val image: ImageView
        internal val firstLine: TextView
        internal val secondLine: TextView

        private val TAG = HomeViewHolder::class.java.simpleName

        init {
            itemView.setOnClickListener(this)
            image = itemView.findViewById(R.id.recycler_item_image)
            firstLine = itemView.findViewById(R.id.first_line)
            secondLine = itemView.findViewById(R.id.second_line)
        }

        override fun setImage(imageUrl: String) {
            Log.d(TAG, "set image with url = $imageUrl")
            Glide.with(itemView)
                .load(imageUrl)
                .apply(RequestOptions().override(1000, 1000))
                .into(image)
        }

        override fun setFirstLine(line: String) {
            Log.d(TAG, "set text to first line = $line")
            this.firstLine.text = line
        }

        override fun setSecondLine(line: String) {
            Log.d(TAG, "set text to second line = $line")
            this.secondLine.text = line
        }

        override fun onClick(v: View) {
            onItemClickedListener!!.onClicked(presenter[adapterPosition])
        }
    }

}
