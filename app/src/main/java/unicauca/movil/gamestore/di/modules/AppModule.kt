package unicauca.movil.gamestore.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Asus on 19/01/2018.
 */

@Module
class AppModule{

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application

    @Singleton
    @Provides
    fun providesPreferences(application: Application): SharedPreferences =
            application.getSharedPreferences("GameStore", 0)


}