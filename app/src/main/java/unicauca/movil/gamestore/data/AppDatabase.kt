package unicauca.movil.gamestore.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import unicauca.movil.gamestore.data.dao.GameDao
import unicauca.movil.gamestore.data.model.Game

/**
 * Created by jlbeltran94 on 19/01/2018.
 */

@Database(entities = [Game::class], version = 1)
abstract class AppDatabase: RoomDatabase(){

    abstract fun gameDao(): GameDao

}