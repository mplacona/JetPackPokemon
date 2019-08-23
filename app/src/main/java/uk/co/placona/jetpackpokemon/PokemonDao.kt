package uk.co.placona.jetpackpokemon

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PokemonDao {
    @Insert
    fun insert(pokemon: Pokemon)
    @Update
    fun update(pokemon: Pokemon)
    @Query("SELECT * FROM pokemons WHERE name LIKE :name")
    fun selectByName(name: String): Pokemon
}