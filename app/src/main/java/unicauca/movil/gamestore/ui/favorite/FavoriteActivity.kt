package unicauca.movil.gamestore.ui.favorite

import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import unicauca.movil.gamestore.R
import unicauca.movil.gamestore.databinding.ActivityFavoriteBinding
import unicauca.movil.gamestore.di.Injectable
import unicauca.movil.gamestore.ui.adapters.FavoriteAdapter
import unicauca.movil.gamestore.ui.adapters.GameAdapter
import unicauca.movil.gamestore.ui.detail.DetailActivity
import unicauca.movil.gamestore.ui.main.MainViewModel
import unicauca.movil.gamestore.utils.LifeDisposable
import unicauca.movil.gamestore.utils.buildViewModel
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity(), Injectable {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val mainViewModel: MainViewModel by lazy { buildViewModel(factory, MainViewModel::class) }
    @Inject
    lateinit var adapter: FavoriteAdapter
    lateinit var binding: ActivityFavoriteBinding
    private val dis: LifeDisposable = LifeDisposable(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_favorite)
        list.adapter = adapter
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        title = "Favoritos"
        getFav()
    }

    override fun onResume() {
        super.onResume()
        dis add adapter.clickGame.subscribeBy(
                onNext = {
                    startActivity<DetailActivity>("game" to it)

                })
        dis add adapter.clickDelete
                .flatMap { mainViewModel.deleteFav(it) }
                .subscribe()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }


    private fun getFav(){
        mainViewModel.loadFav().subscribeBy(
                onNext = {
                    adapter.games = it
                }
        )
    }
}
