package com.example.daggerpractice.displays.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.daggerpractice.R
import com.example.daggerpractice.displays.home.adapter.ItemData
import dagger.android.support.DaggerFragment

class DetailsFragment : DaggerFragment(), DetailsView {

    companion object {
        private const val URL = "URL"
        private const val FIRST_LINE = "FIRST_LINE"
        private const val SECOND_LINE = "SECOND_LINE"

        fun newInstance(itemData: ItemData): DetailsFragment {
            val fragment = DetailsFragment()
            val bundle = Bundle()
            bundle.putString(URL, itemData.imageUrl)
            bundle.putString(FIRST_LINE, itemData.firstLine)
            bundle.putString(SECOND_LINE, itemData.secondLine)
            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.details_fragment_layout, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }
}