package com.test.endtecttest.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.DisplayMetrics
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import java.io.File
import java.io.IOException
import java.io.InputStream

fun ImageView.loadVideoThumbnailWithGlide(context: Context, path: String){
    Glide.with(context)
        .load(Uri.fromFile(File(path)))
        .into(this)
}

fun ImageView.loadVideoThumbnailWithGlide(context: Context, path: String, placeHolder: Int){
    Glide.with(context)
        .load(Uri.parse(path))
        .placeholder(placeHolder)
        .into(this)
}

fun ImageView.loadCircularImage(context: Context, imageView: ImageView, imageUrl: String?, placeHolder: Int) {

    if (imageUrl != null && !imageUrl.isEmpty()) {
        Glide.with(context)
            .load(imageUrl)
            .placeholder(placeHolder)
            .apply(RequestOptions.circleCropTransform())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    } else {
        imageView.setImageResource(placeHolder)
    }

}

fun ImageView.loadImage(context: Context, imageView: ImageView, imageUrl: String?, placeHolder: Int) {

    if (imageUrl != null && !imageUrl.isEmpty()) {
        Glide.with(context)
            .load(imageUrl)
            .placeholder(placeHolder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    } else {
        imageView.setImageResource(placeHolder)
    }

}

fun ImageView.getBitmapFromAsset(context: Context, filePath: String?): Bitmap? {
    val assetManager = context.assets
    val istr: InputStream
    var bitmap: Bitmap? = null
    try {
        istr = assetManager.open(filePath!!)
        bitmap = BitmapFactory.decodeStream(istr)
    } catch (e: IOException) {
        // handle exception
    }
    return bitmap
}

fun String.getBitmapFromAsset(context: Context): Bitmap? {
    val assetManager = context.assets
    val istr: InputStream
    var bitmap: Bitmap? = null
    try {
        istr = assetManager.open(this!!)
        bitmap = BitmapFactory.decodeStream(istr)
    } catch (e: IOException) {
        // handle exception
    }
    return bitmap
}

fun loadImage(context: Context, imageView: ImageView, image: Int, placeHolder: Int) {
    Glide.with(context)
        .load(image)
        .placeholder(placeHolder)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(imageView)

}

fun loadImage(context: Context, imageView: ImageView, image: Bitmap, placeHolder: Int) {
    Glide.with(context)
        .load(image)
        .placeholder(placeHolder)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(imageView)

}

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

fun ImageView.setDrawable(@DrawableRes drawable: Int) {
    setImageDrawable(ContextCompat.getDrawable(context, drawable))
}

fun convertDpToPixel(dp: Float, context: Context): Float {
    return dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}