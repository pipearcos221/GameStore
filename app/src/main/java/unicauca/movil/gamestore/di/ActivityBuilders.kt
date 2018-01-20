package unicauca.movil.gamestore.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import unicauca.movil.gamestore.di.ActivityScope
import unicauca.movil.gamestore.ui.detail.DetailActivity
import unicauca.movil.gamestore.ui.favorite.FavoriteActivity
import unicauca.movil.gamestore.ui.main.MainActivity

/**
 * Created by Asus on 19/01/2018.
 */
@Module
abstract class ActivityBuilders{

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindDetailActivity(): DetailActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindFavoriteActivity(): FavoriteActivity


}