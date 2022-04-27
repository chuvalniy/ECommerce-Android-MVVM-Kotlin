package com.example.ecommercialapplication.core.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.ecommercialapplication.R

object ImageLoader{

    fun loadImage(url: String, container: ImageView) {
        Glide.with(container.context)
            .asBitmap()
            .load(url)
            .into(container)
    }
}