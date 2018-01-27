package unicauca.movil.gamestore.ui.main

import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import unicauca.movil.gamestore.R
import unicauca.movil.gamestore.data.model.Game
import unicauca.movil.gamestore.databinding.ActivityMainBinding
import unicauca.movil.gamestore.di.Injectable
import unicauca.movil.gamestore.ui.adapters.GameAdapter
import unicauca.movil.gamestore.ui.detail.DetailActivity
import unicauca.movil.gamestore.ui.favorite.FavoriteActivity
import unicauca.movil.gamestore.utils.LifeDisposable
import unicauca.movil.gamestore.utils.buildViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Injectable {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val mainViewModel: MainViewModel by lazy { buildViewModel(factory, MainViewModel::class) }
    @Inject
    lateinit var adapter:GameAdapter
    lateinit var binding: ActivityMainBinding
    private val dis:LifeDisposable = LifeDisposable(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        list.adapter = adapter
        adapter.games = mainViewModel.getData()
        title = "Juegos"
        mainViewModel.loadData()

    }

    override fun onResume() {
        super.onResume()
        dis add adapter.clickGame.subscribeBy(
                onNext = {
                    startActivity<DetailActivity>("game" to it)

        })
        dis add adapter.clickFavorite
                .flatMap { mainViewModel.insertFav(it) }
                .subscribe()

        dis add fab.clicks()
                .subscribeBy(
                        onNext = {
                            startActivity<FavoriteActivity>()
                        }
                )
    }



}
