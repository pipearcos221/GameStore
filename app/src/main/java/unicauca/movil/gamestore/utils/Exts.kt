package unicauca.movil.gamestore.utils

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Asus on 18/01/2018.
 */

fun ViewGroup.inflate(@LayoutRes layout: Int): View =
        LayoutInflater.from(context).inflate(layout, this, false)