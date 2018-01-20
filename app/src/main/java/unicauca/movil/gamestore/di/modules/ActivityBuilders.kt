package unicauca.movil.gamestore.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import unicauca.movil.gamestore.di.ActivityScope
import unicauca.movil.gamestore.ui.main.MainActivity

/**
 * Created by Asus on 19/01/2018.
 */
@Module
abstract class ActivityBuilders{

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindLoginActivity(): MainActivity


}