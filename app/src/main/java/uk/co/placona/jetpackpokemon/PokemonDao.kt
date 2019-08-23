package uk.co.placona.jetpackpokemon

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pokemon: Pokemon)
    @Update
    fun update(pokemon: Pokemon)
    @Query("DELETE FROM pokemons WHERE name = :name")
    fun deleteByName(name: String)
    @Query("SELECT * FROM pokemons WHERE name LIKE :name")
    fun selectByName(name: String): LiveData<Pokemon>


}