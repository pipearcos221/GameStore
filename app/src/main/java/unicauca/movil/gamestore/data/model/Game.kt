package unicauca.movil.gamestore.data.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Created by Asus on 18/01/2018.
 */
@SuppressLint("ParcelCreator")
@Parcelize
class Game (val img:String,
            val name:String?,
            val price: Int,
            val role: Date,
            val description: String): Parcelable