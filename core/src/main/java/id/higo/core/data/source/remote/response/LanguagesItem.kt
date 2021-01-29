package id.higo.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class LanguagesItem(

    @field:SerializedName("nativeName")
    val nativeName: String,

    @field:SerializedName("name")
    val name: String
)
