package unicauca.movil.gamestore.data.model

import android.annotation.SuppressLint
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Created by Asus on 18/01/2018.
 */
@Entity
@SuppressLint("ParcelCreator")
@Parcelize
class Game (@PrimaryKey(autoGenerate = true) val id: Long?,
            val img:String,
            val name:String?,
            val price: Int,
            val role: String,
            val description: String): Parcelable{

    @Ignore
    constructor(img: String, name: String?, price: Int, role: String,description: String):this(null,img,name,price,role,description)
}