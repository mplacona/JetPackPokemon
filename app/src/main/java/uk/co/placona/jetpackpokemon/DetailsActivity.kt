package uk.co.placona.jetpackpokemon

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide

class DetailsActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_details)

        val model = ViewModelProviders.of(this).get(PokemonViewModel::class.java)
        val ivPokemonImage = findViewById<ImageView>(R.id.pokemon_image)

        val pokemonId:Int
        val extras = intent.extras
        pokemonId = extras?.getInt("POKEMON_ID") ?: 1

        pokemonId.let {
            model.getPokemon(it).observe(this,
                Observer<PokemonDetails> { pokemon ->
                    findViewById<TextView>(R.id.pokemon_name).apply {
                        text = pokemon.capitalizedName
                    }

                    if(pokemonId == 1){
                        findViewById<TextView>(R.id.pokemon_name).apply {
                            text = "Cute Bulbasaur"
                        }
                    }

                    findViewById<TextView>(R.id.pokemon_height).apply {
                        text = pokemon.height.toString() + " dm"
                    }

                    findViewById<TextView>(R.id.pokemon_weight).apply {
                        text = pokemon.weight.toString() + " hg"
                    }

                    val pokemonImage = applicationContext.getString(
                        R.string.pokemon_image_url,
                        String.format("%03d", pokemon.id)
                    )

                    Glide.with(applicationContext)
                        .load(pokemonImage)
                        .into(ivPokemonImage);
                })
        }
    }
}
