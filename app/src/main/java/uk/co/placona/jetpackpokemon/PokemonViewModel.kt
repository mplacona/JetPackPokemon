package uk.co.placona.jetpackpokemon

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PokemonViewModel: ViewModel() {
    private val tag = MainActivity::class.java.simpleName
    private val pokeClient: PokeClient = PokeClient()
    //this is the data that we will fetch asynchronously
    lateinit var pokemonList: MutableLiveData<List<Result>>


    //we will call this method to get the data
    fun getPokemons(): LiveData<List<Result>> {
        //if the list is null

        if(!::pokemonList.isInitialized){
            pokemonList = MutableLiveData()
            //we will load it asynchronously from server in this method
            Log.d(tag, "Loading pokemons")
            loadPokemons()
        }

        //finally we will return the list
        return pokemonList
    }

    //This method is using Retrofit to get the JSON data from URL
    private fun loadPokemons() {

        val pokemons = pokeClient.getPokemon()
        pokemons.enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                pokemonList.value = response.body()?.results
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                Log.d("ERROR", "Failure " + t.message)
            }
        })
    }
}