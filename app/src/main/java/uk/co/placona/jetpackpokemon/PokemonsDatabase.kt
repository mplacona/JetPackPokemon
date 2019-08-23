package uk.co.placona.jetpackpokemon

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Pokemon::class], version = 1)
abstract class PokemonsDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}