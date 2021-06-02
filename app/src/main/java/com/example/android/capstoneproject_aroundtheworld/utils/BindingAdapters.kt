package com.example.android.capstoneproject_aroundtheworld.utils

import android.graphics.drawable.PictureDrawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.android.capstoneproject_aroundtheworld.R
import com.example.android.capstoneproject_aroundtheworld.network.svgloader.SvgSoftwareLayerSetter

@BindingAdapter("imageUrl")
fun bindCountryFlag(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(imageView.context)
            .`as`(PictureDrawable::class.java)
                .load(imageUrl)
                .centerCrop()
                .override(10, 10)
                .placeholder(R.drawable.ic_launcher_background)
            .listener(SvgSoftwareLayerSetter())
                .into(imageView)
    }
}