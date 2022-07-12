package com.sirius.test_app

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.sirius.test_app.databinding.ActivityMainBinding
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()

        initSystemBarsDimensionChangesListener()

        loadHeaderImage()

        loadGameImage()
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