package id.higo.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CountryResponse(

    @field:SerializedName("area")
    val area: Double,

    @field:SerializedName("capital")
    val capital: String,

    @field:SerializedName("flag")
    val flag: String,

    @field:SerializedName("languages")
    val languages: List<LanguagesItem>,

    @field:SerializedName("subregion")
    val subregion: String,

    @field:SerializedName("callingCodes")
    val callingCodes: List<String>,

    @field:SerializedName("population")
    val population: Int,

    @field:SerializedName("topLevelDomain")
    val topLevelDomain: List<String>,

    @field:SerializedName("timezones")
    val timezones: List<String>,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("region")
    val region: String,

    @field:SerializedName("latlng")
    val latlng: List<Double>?,

    @field:SerializedName("currencies")
    val currencies: List<CurrenciesItem>?
)
