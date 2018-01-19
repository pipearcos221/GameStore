package unicauca.movil.gamestore.utils

import android.databinding.BindingAdapter
import android.net.Uri
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by Asus on 18/01/2018.
 */

@BindingAdapter("app:imgUrl")
fun setImageByUrl(image: ImageView, url: String){
    Picasso.with(image.context)
            .load(Uri.parse(url))
            .into(image)
}