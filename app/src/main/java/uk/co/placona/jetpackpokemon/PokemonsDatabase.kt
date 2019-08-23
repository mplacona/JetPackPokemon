package uk.co.placona.jetpackpokemon

import androidx.room.Database

@Database(entities = [Pokemon::class], version = 1)
abstract class PokemonsDatabase {
}