package com.sirius.test_app.ui.mainActivity.recyclerViewAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sirius.test_app.R
import com.sirius.test_app.databinding.TagItemBinding

class TagsRecyclerViewAdapter(
    private val tags: List<String>
) : RecyclerView.Adapter<TagsRecyclerViewAdapter.TagHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagHolder {
        return TagHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.tag_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TagHolder, position: Int) {
        with(tags[position]) {
            holder.bind(this)
        }
    }

    override fun getItemCount(): Int = tags.size

    inner class TagHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        private val binding = TagItemBinding.bind(view)

        fun bind(tag: String) {
            binding.tag.text = tag
        }
    }
}