package unicauca.movil.gamestore.ui.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import unicauca.movil.gamestore.R
import unicauca.movil.gamestore.data.model.Game
import unicauca.movil.gamestore.utils.inflate


/**
 * Created by Asus on 18/01/2018.
 */

class GameAdapter: RecyclerView.Adapter<GameHolder>(){

    var onGameSelected:((position:Int)->Unit)? = null
    var onFavoriteSelected:((position:Int)->Unit)? = null

    var data : List<Game> = emptyList()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: GameHolder, position: Int) {
        holder.bind(data[position], position, this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder =
            GameHolder(parent.inflate(R.layout.template_game))

    override fun getItemCount(): Int = data.size

    fun onClickFavorite(position: Int){
        onFavoriteSelected?.invoke(position)
    }

    fun onClickGame(position: Int){
        onGameSelected?.invoke(position)
    }

}

class GameHolder(view: View): RecyclerView.ViewHolder(view){

    private val binding:TemplateGameBinding = DataBindingUtil.bind(view)

    fun bind(game: Game, position: Int, handler: GameAdapter){
        binding.game = game
        binding.position = position
        binding.handler = handler
    }

}