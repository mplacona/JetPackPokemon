package uk.co.placona.jetpackpokemon
import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Pokemon>
)

data class Pokemon(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)