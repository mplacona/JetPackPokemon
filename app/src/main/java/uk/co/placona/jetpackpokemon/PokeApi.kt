package uk.co.placona.jetpackpokemon

import retrofit2.Call
import retrofit2.http.GET

interface PokeApi {
    @GET("pokemon/")
    fun getPokemons(): Call<Results>
}