package by.eapp.gamesearcher.data
import com.google.gson.annotations.SerializedName


data class Game(
    @SerializedName("id")
    val gameID: Long,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("name")
    val title: String,
    @SerializedName("released")
    val dateOfRelease: String,
    @SerializedName("platform")
    val platform: String,
    @SerializedName("background_image")
    val backgroundImage: String,
    @SerializedName("background_image_additional")
    val backgroundImageAdditional: String

)
