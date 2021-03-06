package uk.co.placona.jetpackpokemon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item.view.*
import java.util.regex.Pattern

class PokemonAdapter(private val pokemons: List<Pokemon>) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    override fun getItemCount() = pokemons.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val number = getPokemonId(pokemons[position].url)
        holder.pokemonName.text = pokemons[position].name.capitalize()
        holder.pokemonNumber.text = "# $number"
        Glide.with(holder.pokemonImage.context).load("http://www.serebii.net/pokemongo/pokemon/${String.format("%03d", number.toInt())}.png").
            into(holder.pokemonImage)

    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val pokemonName: TextView = view.pokemon_name
        val pokemonImage: ImageView = view.pokemon_image
        val pokemonNumber: TextView = view.number
    }

    private fun getPokemonId(pokemonUrl: String): String {
        val pattern = ".*\\/pokemon\\/([0-9]+)"
        val compiledPattern = Pattern.compile(pattern)
        val matcher = compiledPattern.matcher(pokemonUrl)
        return if (matcher.find()) {
            matcher.group(1)
        } else {
            "error"
        }
    }
}