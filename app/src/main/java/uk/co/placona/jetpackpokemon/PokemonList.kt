package uk.co.placona.jetpackpokemon

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class PokemonList : AppCompatActivity() {

    private val tag = PokemonList::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_list)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview_pokemon)


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        val model = ViewModelProviders.of(this).get(PokemonViewModel::class.java)

        model.getPokemons().observe(this,
            Observer<List<Pokemon>> { pokemonList ->
                recyclerView.adapter = PokemonAdapter(pokemonList)
            })

        recyclerView.addOnItemClickListener(object: OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                Log.d(tag, "clicked")
                Navigation.findNavController(this@PokemonList, R.id.nav_host).navigate(R.id.pokemonDetails)
            }
        })
    }
}
