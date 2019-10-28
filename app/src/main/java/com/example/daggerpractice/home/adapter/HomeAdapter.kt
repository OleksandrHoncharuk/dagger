package com.example.daggerpractice.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.daggerpractice.R
import java.util.ArrayList

class HomeAdapter(private val presenter: ListPresenter) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var onItemClickedListener: OnItemClickedListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): HomeViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.recycler_item, null))
    }

    fun setOnItemClickedListener(onItemClickedListener: OnItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener
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
        presenter.refreshSearch(items)
    }

    inner class HomeViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView), RowView, View.OnClickListener {
        private val image: ImageView
        internal val firstLine: TextView
        internal val secondLine: TextView

        init {

            itemView.setOnClickListener(this)
            image = itemView.findViewById(R.id.recycler_item_image)
            firstLine = itemView.findViewById(R.id.first_line)
            secondLine = itemView.findViewById(R.id.second_line)
        }

        override fun setImage(imageUrl: String) {
            Glide.with(itemView)
                .load(imageUrl)
                .into(image)
        }

        override fun setFirstLine(line: String) {
            this.firstLine.text = line
        }

        override fun setSecondLine(line: String) {
            this.secondLine.text = line
        }

        override fun onClick(v: View) {
            onItemClickedListener!!.onClicked(presenter[adapterPosition])
        }
    }

}
