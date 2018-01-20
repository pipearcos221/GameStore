package unicauca.movil.gamestore.data.dao

import android.arch.persistence.room.*
import io.reactivex.Flowable
import unicauca.movil.gamestore.data.model.Game

/**
 * Created by jlbeltran94 on 19/01/2018.
 */
@Dao
interface GameDao{

    @Insert
    fun insert(game: Game)

    @Update
    fun update(game: Game)

    @Delete
    fun delete(game: Game)

    @Query("SELECT * FROM game GROUP BY name")
    fun all():Flowable<List<Game>>

}