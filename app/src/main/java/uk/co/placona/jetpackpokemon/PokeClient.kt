package uk.co.placona.jetpackpokemon

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokeClient {
    private var pokeApi: PokeApi
    private val baseUrl = "${BuildConfig.API_URL}/api/v2/"

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        val httpClient = OkHttpClient.Builder().addInterceptor(logging).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        pokeApi = retrofit.create(PokeApi::class.java)
    }

    fun getPokemon(): Call<Results> = pokeApi.getPokemons()
}