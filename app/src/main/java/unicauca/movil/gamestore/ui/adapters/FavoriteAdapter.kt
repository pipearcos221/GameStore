package unicauca.movil.gamestore.ui.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import io.reactivex.subjects.PublishSubject
import unicauca.movil.gamestore.R
import unicauca.movil.gamestore.data.model.Game
import unicauca.movil.gamestore.databinding.TemplateFavoriteBinding
import unicauca.movil.gamestore.databinding.TemplateGameBinding
import unicauca.movil.gamestore.utils.inflate
import javax.inject.Inject

/**
 * Created by jlbeltran94 on 20/01/2018.
 */
class FavoriteAdapter @Inject constructor(): RecyclerView.Adapter<FavoriteHolder>(){

    val clickGame = PublishSubject.create<Game>()
    val clickDelete = PublishSubject.create<Game>()

    var games : List<Game> = emptyList()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHolder =
            FavoriteHolder(parent.inflate(R.layout.template_favorite))

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        holder.binding.game = games[position]
        holder.binding.clickGame = clickGame
        holder.binding.clickDelete = clickDelete

    }

    override fun getItemCount(): Int = games.size

}

class FavoriteHolder(view: View): RecyclerView.ViewHolder(view){
    val binding: TemplateFavoriteBinding = DataBindingUtil.bind(view)
}