package id.higo.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Country(
    val countryId: String,
    val countryName: String,
    val flag: String,
    val capital: String,
    val region: String,
    val callingCode: String,
    val subRegion: String,
    val language: String,
    val topLevelDomain: String,
    val currenciesCode: String?,
    val currenciesName: String?,
    val currenciesSymbol: String?,
    val timeZone: String,
    val latitude: Double,
    val longitude: Double,
    val population: Int,
    val area: Double,
    val isFavorite: Boolean
): Parcelable