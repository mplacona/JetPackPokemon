package uk.co.placona.jetpackpokemon

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide

class DetailsActivity : AppCompatActivity() {

    private val tag = DetailsActivity::class.java.simpleName
    private lateinit var pokemonRepository: PokemonRepository
    private lateinit var pokemonName:String
    private lateinit var pokemonObject:PokemonDetails
    private lateinit var pokemonExists: LiveData<Pokemon>

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_details)

        pokemonRepository = PokemonRepository(applicationContext)

        val model = ViewModelProviders.of(this).get(PokemonViewModel::class.java)
        val ivPokemonImage = findViewById<ImageView>(R.id.pokemon_image)
        val btnPet = findViewById<Button>(R.id.pet_btn)

        val pokemonId:Int
        val extras = intent.extras
        pokemonId = extras?.getInt("POKEMON_ID") ?: 1

        pokemonId.let { it ->
            model.getPokemon(it).observe(this,
                Observer<PokemonDetails> { pokemon ->
                    pokemonObject = pokemon
                    pokemonName = pokemon.capitalizedName
                    pokemonExists = pokemonRepository.selectByName(pokemonName)


                    pokemonExists.observe(this, Observer { it ->
                        pokemonName = if(pokemonExists.value != null){
                            pokemonExists.value?.name + " (Pet)"
                        }else{
                            pokemon.capitalizedName
                        }
                        findViewById<TextView>(R.id.pokemon_name).apply {
                            text = pokemonName
                        }
                    })



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

        btnPet.setOnClickListener {
            if(pokemonExists.value != null){
                pokemonRepository.deleteByName(pokemonObject.capitalizedName)
            }else{
                pokemonRepository.insert(Pokemon(pokemonObject.capitalizedName, ""))
            }
        }
    }
}
