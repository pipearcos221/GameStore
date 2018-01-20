package unicauca.movil.gamestore.di.modules

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import unicauca.movil.gamestore.data.AppDatabase
import unicauca.movil.gamestore.data.dao.GameDao
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


    @Singleton
    @Provides
    fun provideDatabase(context: Context) : AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "games.db")
                    .build()

    @Singleton
    @Provides
    fun providesGameDao(appDatabase: AppDatabase): GameDao =
            appDatabase.gameDao()


}