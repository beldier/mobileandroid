package com.example.restaurant.utils

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import com.example.restaurant.R
import com.example.restaurant.viewmodel.Popularity

@BindingAdapter("app:popularityIcon")
fun popularityIcon(view: ImageView, popularity: Popularity){
    val color = getAssociatedColor(popularity, view.context)
    ImageViewCompat.setImageTintList(view, ColorStateList.valueOf(color as Int))
    view.setImageDrawable(getDrawablePupularity(popularity,view.context))
}

@BindingAdapter("app:progressTint")
fun tintPopularity(view: ProgressBar, popularity: Popularity){
    val color = getAssociatedColor(popularity,view.context)
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
        view.progressTintList = ColorStateList.valueOf(color as Int)
    }
}

@BindingAdapter(value =["app:progressScaled","android:max"],requireAll = true)
fun setProgress(progressBar: ProgressBar,likes: Int, max:Int){
    progressBar.progress = (likes * max / 5).coerceAtMost(max)
}

@BindingAdapter("app:hideIfZero")
fun hideIfZero(view: View, number:Int){
    view.visibility = if ( number == 0 ) View.GONE else View.VISIBLE
}
fun getAssociatedColor(popularity: Popularity, context: Context?): Any {
    return when(popularity){
        Popularity.NORMAL -> ContextCompat.getColor(context!!, R.color.colorPrimary)
        Popularity.POPULAR -> ContextCompat.getColor(context!!, R.color.popular)
        Popularity.STAR -> ContextCompat.getColor(context!!,R.color.star)
    }
}

private fun getDrawablePupularity(popularity: Popularity, context: Context):Drawable?{
    return when(popularity) {
        Popularity.NORMAL -> {
            ContextCompat.getDrawable(context, R.drawable.ic_person_black)
        }

        Popularity.POPULAR -> {
            ContextCompat.getDrawable(context, R.drawable.ic_whatshot_black_foreground)
        }
        Popularity.STAR -> {
            ContextCompat.getDrawable(context, R.drawable.ic_whatshot_black_foreground)
        }
    }
}