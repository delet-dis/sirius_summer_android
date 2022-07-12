package com.sirius.test_app.ui.mainActivity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.sirius.test_app.R
import com.sirius.test_app.data.DataModel
import com.sirius.test_app.databinding.ActivityMainBinding
import com.sirius.test_app.ui.mainActivity.recyclerViewAdapters.RatingRecyclerViewAdapter
import kotlin.math.roundToInt
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val dataModel = DataModel()

    private var statusBarHeight by Delegates.notNull<Int>()

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    private fun initSystemBarsDimensionChangesListener() =
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _: View?, insets: WindowInsetsCompat ->
            statusBarHeight = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top

            changeTopBarHeight(statusBarHeight)

            WindowInsetsCompat.CONSUMED
        }

    private fun changeTopBarHeight(statusBarHeight: Int) {
        val buttonsTopInset = resources.getDimension(R.dimen.transparentCardTopInset).toInt()
        val recalculatedButtonsTopInset = buttonsTopInset + statusBarHeight

        with(binding) {
            with(backCard.layoutParams as ViewGroup.MarginLayoutParams) {
                topMargin = recalculatedButtonsTopInset
            }

            with(optionsCard.layoutParams as ViewGroup.MarginLayoutParams) {
                topMargin = recalculatedButtonsTopInset
            }
        }
    }

    private fun loadHeaderImage() {
        Glide.with(this)
            .load(dataModel.image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(options)
            .into(binding.headerImage)
    }

    private fun loadGameImage() {
        Glide.with(this)
            .load(dataModel.logo)
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(options)
            .into(binding.gameImage)
    }

    private fun initGameHeader() {
        binding.gameHeader.text = dataModel.name
    }

    private fun initNumberOfReviews() {
        binding.numberOfReviews.text = dataModel.gradeCnt
    }

    private fun initRatingRecycler() {
        with(binding.ratingRecycler) {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)

            adapter = RatingRecyclerViewAdapter(dataModel.rating.roundToInt())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()

        initSystemBarsDimensionChangesListener()

        loadHeaderImage()

        loadGameImage()

        initGameHeader()

        initNumberOfReviews()

        initRatingRecycler()
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