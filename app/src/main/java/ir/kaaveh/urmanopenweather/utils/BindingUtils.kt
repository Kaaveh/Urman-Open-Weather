package ir.kaaveh.urmanopenweather.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

const val URL_IMAGE = "http://openweathermap.org/img/wn/"

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, icon: String?) {
    val url = "$URL_IMAGE$icon@2x.png"
    Glide.with(view)
        .load(url)
        .into(view)
}