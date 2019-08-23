package uk.co.placona.jetpackpokemon

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.room.Room

@SuppressLint("StaticFieldLeak")
class PokemonRepository constructor(context: Context) : PokemonDao {
    private val dbName = "pokemon_db"
    private var pokemonsDatabase: PokemonsDatabase

    init{
        pokemonsDatabase = Room.databaseBuilder(context, PokemonsDatabase::class.java, dbName).build()
    }

    override fun insert(pokemon: Pokemon) {

        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                pokemonsDatabase.pokemonDao().insert(pokemon)
                return null
            }
        }.execute()
    }

    override fun update(pokemon: Pokemon) {
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                pokemonsDatabase.pokemonDao().update(pokemon)
                return null
            }
        }.execute()
    }

    override fun deleteByName(name: String) {
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                pokemonsDatabase.pokemonDao().deleteByName(name)
                return null
            }
        }.execute()
    }

    override fun selectByName(name: String): LiveData<Pokemon> {
        return pokemonsDatabase.pokemonDao().selectByName(name)
    }


}