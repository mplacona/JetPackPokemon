package uk.co.placona.jetpackpokemon

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {
    @GET("pokemon/")
    fun getPokemons(): Call<Results>

    @GET("pokemon/{id}")
    fun getPokemon(@Path("id") pokemonId: Int): Call<PokemonDetails>
}