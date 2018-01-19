package unicauca.movil.gamestore.ui.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import io.reactivex.subjects.PublishSubject
import unicauca.movil.gamestore.R
import unicauca.movil.gamestore.data.model.Game
import unicauca.movil.gamestore.databinding.TemplateGameBinding
import unicauca.movil.gamestore.utils.inflate


/**
 * Created by Asus on 18/01/2018.
 */

class GameAdapter: RecyclerView.Adapter<GameHolder>(){

    val clickGame = PublishSubject.create<Game>()
    val clickFavorite = PublishSubject.create<Game>()

    var games : List<Game> = emptyList()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder =
            GameHolder(parent.inflate(R.layout.template_game))

    override fun onBindViewHolder(holder: GameHolder, position: Int) {
        holder.binding.game = games[position]
        holder.binding.clickGame = clickGame
        holder.binding.clickFavorite = clickFavorite

    }

    override fun getItemCount(): Int = games.size

}

class GameHolder(view: View): RecyclerView.ViewHolder(view){
    val binding: TemplateGameBinding = DataBindingUtil.bind(view)
}