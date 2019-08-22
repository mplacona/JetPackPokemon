package uk.co.placona.jetpackpokemon

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private val pokeClient: PokeClient = PokeClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview_pokemon)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        val pokemons = pokeClient.getPokemon()
        pokemons.enqueue(object : Callback<Results> {
            override fun onResponse(call: Call<Results>, response: Response<Results>) {
                val pokemonList: List<Pokemon> = response.body()!!.results
                recyclerView.adapter = PokemonAdapter(pokemonList)
            }

            override fun onFailure(call: Call<Results>, t: Throwable) {
                Log.d("ERROR", "Failure " + t.message)
            }
        })

    }
}
