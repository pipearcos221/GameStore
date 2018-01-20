package unicauca.movil.gamestore

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import unicauca.movil.gamestore.di.AppInjector
import javax.inject.Inject

/**
 * Created by Asus on 19/01/2018.
 */
class App : Application(), HasActivityInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity>
            = injector



    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }
}