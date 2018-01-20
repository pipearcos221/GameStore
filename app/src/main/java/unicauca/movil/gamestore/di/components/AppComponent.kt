package unicauca.movil.gamestore.di.components

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import unicauca.movil.gamestore.App
import unicauca.movil.gamestore.di.modules.ActivityBuilders
import unicauca.movil.gamestore.di.modules.AppModule
import unicauca.movil.gamestore.di.modules.ViewModelModule
import javax.inject.Singleton

/**
 * Created by Asus on 19/01/2018.
 */
@Singleton
@Component(modules = [
    (AndroidInjectionModule::class),
    (AppModule::class),
    (ActivityBuilders::class),
    (ViewModelModule::class)
])
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicacion(application: Application): Builder
        fun build(): AppComponent
    }

}