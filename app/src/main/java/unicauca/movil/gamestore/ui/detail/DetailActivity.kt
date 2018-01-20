package unicauca.movil.gamestore.ui.detail

import android.databinding.DataBindingUtil
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.view.MenuItem
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import unicauca.movil.gamestore.R
import unicauca.movil.gamestore.data.model.Game
import unicauca.movil.gamestore.databinding.ActivityDetailBinding
import unicauca.movil.gamestore.di.Injectable

class DetailActivity : AppCompatActivity(), Injectable, Callback {

    lateinit var binding: ActivityDetailBinding
    lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        game = intent.extras.getParcelable("game")
        binding.game = game
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Picasso.with(this)
                .load(Uri.parse(game.img))
                .into(img, this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onSuccess() {
        val drawable = img.drawable as BitmapDrawable
        val palette = Palette.from(drawable.bitmap).generate()

        val primaryColor = ContextCompat.getColor(this, R.color.colorPrimary)
        val vibrantColor = palette.getVibrantColor(primaryColor)

        collpasingToolbar.setContentScrimColor(vibrantColor)
        setStatusBarColor(vibrantColor)
    }

    override fun onError() {}

    fun setStatusBarColor(color:Int){
        var blue = Color.blue(color) - 30
        var red = Color.red(color) - 30
        var green = Color.green(color) - 30

        blue = if(blue < 0) 0 else blue
        red = if(red < 0) 0 else red
        green = if(green < 0) 0 else green

        val statusColor = Color.rgb(red, green, blue)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = statusColor
        }
    }
}
