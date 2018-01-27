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
            with(data){
                add(Game(
                        "http://gamedustria.com/wp-content/uploads/2017/10/Assassins-Creed-Origins.jpg",
                        "Assassin's Creed Origins",
                        180000,
                        "Acción,Aventura,Ciencia ficción",
                        "Ambientado en el misterioso Antiguo Egipto, Assassin’s Creed® Origins es un nuevo comienzo."
                ))
                add(Game(
                        "https://www.tec.com.pe/wp-content/uploads/2016/08/Legion-World-of-Warcraft.jpg",
                        "World of WarCraft Legion",
                        150000,
                        "MMORPG",
                        "La Tumba de Sargeras se ha reabierto, y ahora los demonios de la Legión Ardiente entran a raudales en nuestro mundo."))
                add(Game(
                        "https://cdn.ndtv.com/tech/gadgets/league_of_legends.jpg",
                        "League of Legends",
                        0,
                        "MOBA",
                        "League of Legends es un videojuego de género multiplayer online battle arena y deporte electrónico desarrollado por Riot Games para Microsoft Windows y OS X."))
                add(Game(
                        "http://static.alfabetajuega.com/abj_public_files/multimedia/imagenes/Alfabetajuega-Overwatch.jpg",
                        "Overwatch",
                        180000,
                        "FPS",
                        "Overwatch es un videojuego de tipo shooter en equipos en primera persona desarrollado por Blizzard Entertainment. Fue lanzado al público en mayo del 2016 para Microsoft Windows, Playstation 4 y Xbox One."))
                add(Game(
                        "https://www.geektowers.com/wp-content/uploads/2017/05/gtavverify-feature-geektowers.jpg",
                        "Grand Theft Auto V",
                        180000,
                        "Acción, aventura, mundo abierto",
                        "Grand Theft Auto V es un videojuego de acción-aventura de mundo abierto desarrollado por la compañía británica Rockstar North y distribuido por Rockstar Games. Fue lanzado el 17 de septiembre de 2013 para las consolas PlayStation 3 y Xbox 360."))
            }
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