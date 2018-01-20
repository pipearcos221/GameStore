package unicauca.movil.gamestore.ui.main

import android.arch.lifecycle.ViewModel
import io.reactivex.Flowable
import io.reactivex.Observable
import unicauca.movil.gamestore.data.dao.GameDao
import unicauca.movil.gamestore.data.model.Game
import unicauca.movil.gamestore.utils.applySchedulers
import javax.inject.Inject

/**
 * Created by Asus on 19/01/2018.
 */
class MainViewModel @Inject constructor(private val dao:GameDao):ViewModel(){

    private var data:MutableList<Game> = mutableListOf()

    fun loadData(){
        if (data.isEmpty()) {
            data.add(Game(
                    "http://gamedustria.com/wp-content/uploads/2017/10/Assassins-Creed-Origins.jpg",
                    "Assassin's Creed Origins",
                    180000,
                    "Acción,Aventura,Ciencia ficción",
                    "Ambientado en el misterioso Antiguo Egipto, Assassin’s Creed® Origins es un nuevo comienzo."
            ))
            data.add(Game(
                    "https://www.tec.com.pe/wp-content/uploads/2016/08/Legion-World-of-Warcraft.jpg",
                    "World of WarCraft Legion",
                    150000,
                    "MMORPG",
                    "La Tumba de Sargeras se ha reabierto, y ahora los demonios de la Legión Ardiente entran a raudales en nuestro mundo."))
        }

    }
    fun loadFav():Flowable<List<Game>> = dao.all().applySchedulers()

    fun insertFav(game: Game): Observable<Unit> =
            Observable.fromCallable { dao.insert(game) }
                    .applySchedulers()
    fun deleteFav(game: Game):Observable<Unit> =
            Observable.fromCallable { dao.delete(game) }
                    .applySchedulers()

    fun getData():MutableList<Game> = data

}