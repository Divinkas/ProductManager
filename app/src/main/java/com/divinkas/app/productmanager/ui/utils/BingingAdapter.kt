package com.divinkas.app.productmanager.ui.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BingingAdapter {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageByRes(imageView: ImageView, url: String?) {
        Glide.with(imageView.context).load(url).into(imageView)
    }
}