package com.test.endtecttest.util


import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


fun ImageView.loadImage(imageUrl: String?, @DrawableRes placeholder: Int) {
    if (imageUrl.isNullOrEmpty()) {
        setImageDrawable(ContextCompat.getDrawable(context, placeholder))
    } else {
        Glide.with(context)
            .load(imageUrl)
            .placeholder(placeholder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(this)
    }
}