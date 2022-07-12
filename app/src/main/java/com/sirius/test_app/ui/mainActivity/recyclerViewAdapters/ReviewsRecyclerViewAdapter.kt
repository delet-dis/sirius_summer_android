package com.sirius.test_app.ui.mainActivity.recyclerViewAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.sirius.test_app.R
import com.sirius.test_app.data.ReviewModel
import com.sirius.test_app.databinding.ReviewItemBinding

class ReviewsRecyclerViewAdapter(
    private val reviews: List<ReviewModel>
) : RecyclerView.Adapter<ReviewsRecyclerViewAdapter.ReviewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewHolder {
        return ReviewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.review_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ReviewHolder, position: Int) {
        with(reviews[position]) {
            holder.bind(this)
        }
    }

    override fun getItemCount(): Int = reviews.size

    inner class ReviewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        private val binding = ReviewItemBinding.bind(view)

        fun bind(review: ReviewModel) {
            Glide.with(binding.root.context)
                .load(review.userImage)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(options)
                .into(binding.authorImage)

            binding.reviewDate.text = review.date

            binding.reviewText.text = review.message

            binding.authorName.text = review.userName
        }
    }

    private companion object {
        private val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.progress_animation)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .dontAnimate()
            .dontTransform()
    }
}