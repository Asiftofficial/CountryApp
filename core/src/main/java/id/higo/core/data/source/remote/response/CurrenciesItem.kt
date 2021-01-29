package id.higo.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CurrenciesItem(

    @field:SerializedName("symbol")
    val symbol: String,

    @field:SerializedName("code")
    val code: String,

    @field:SerializedName("name")
    val name: String
)
