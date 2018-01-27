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
                        "https://www.sravni.bg/product-images-r-1500x1500/overwatch-origins-edition-pc-film-podarak-000000924581.jpg",
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
                add(Game(
                        "https://gameplanet-53f8.kxcdn.com/media/catalog/product/cache/4/thumbnail/9df78eab33525d08d6e5fb8d27136e95/b/r/breath-of-the-wild.jpg",
                        "The Legend of Zelda: Breath of the Wild",
                        120000,
                        "RPG",
                        "Sumérgete en un mundo de aventuras, exploración y descubrimientos en The Legend of Zelda™: Breath of the Wild."))
                add(Game(
                        "http://as01.epimg.net/esports/imagenes/2018/01/23/portada/1516729103_695275_1516729169_noticia_normal.jpg",
                        "Fifa 18",
                        180000,
                        "Deportes",
                        "EA SPORTS™ FIFA 18 hace que se confunda la línea que separa el mundo virtual del real, y consigue darle vida a los jugadores, a los equipos y a las atmósferas que se viven en el juego del mundo."))
                add(Game(
                        "https://http2.mlstatic.com/pro-evolution-soccer-18-pes-2018-pc-codigo-steam-garantia-D_NQ_NP_641392-MLA26065519204_092017-F.jpg",
                        "Pro Evolution Soccer 2018",
                        150000,
                        "Deportes",
                        "PES 2018 es la apuesta de Konami en el terreno de la simulación deportiva de fútbol para la temporada 2017-2018. El videojuego cuenta en esta ocasión con el denominado Juego Magistral, que ofrece elementos como el toque realista, los regates estratégicos y una atención renovada por el juego a balón parado, algo olvidada en las entregas de esta serie de fútbol. Así que en este sentido Pro Evolution Soccer 2018 apuesta por ofrecer mayor realismo en las fintas y los pases, así como en los golpes francos y faltas indirectas."))
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