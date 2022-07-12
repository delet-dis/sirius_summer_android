package com.sirius.test_app.ui.mainActivity.recyclerViewAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sirius.test_app.R

class RatingRecyclerViewAdapter(
    private val rating: Int
) : RecyclerView.Adapter<RatingRecyclerViewAdapter.RatingHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingHolder {
        return RatingHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.rating_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RatingHolder, position: Int) {}

    override fun getItemCount(): Int = rating

    inner class RatingHolder(view: View) :
        RecyclerView.ViewHolder(view) {}
}