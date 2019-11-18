package com.example.daggerpractice.displays.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.daggerpractice.R
import com.example.daggerpractice.displays.MainActivity
import com.example.daggerpractice.displays.home.adapter.ItemData
import dagger.android.support.DaggerFragment

class DetailsFragment : DaggerFragment(), DetailsView {

    private lateinit var url: String
    private lateinit var firstLine: String
    private lateinit var secondLine: String

    override fun onResume() {
        (activity as MainActivity).setToolbarParameters(true, firstLine)
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = true
        setHasOptionsMenu(true)

        if (arguments != null) {
            url = arguments!!.getString(URL)!!
            firstLine = arguments!!.getString(FIRST_LINE)!!
            secondLine = arguments!!.getString(SECOND_LINE)!!
        }

        (activity as MainActivity).setToolbarParameters(true, firstLine)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.details_fragment_layout, container, false)
        val firstTextView = root.findViewById<TextView>(R.id.first)
        val secondTextView = root.findViewById<TextView>(R.id.second)

        (activity as MainActivity).setCircleImage(url)

        firstTextView.text = firstLine
        secondTextView.text = secondLine

        return root
    }

    override fun onDetach() {
        super.onDetach()
        (activity as MainActivity).expandCollapseAppBar(false)
        (activity as MainActivity).lockAppBar()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                activity!!.onBackPressed()
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }


    }

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
}