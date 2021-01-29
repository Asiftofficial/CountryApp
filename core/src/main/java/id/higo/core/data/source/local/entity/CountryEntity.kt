package id.higo.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class CountryEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "countryId")
    var countryId: String,

    @ColumnInfo(name = "countryName")
    var countryName: String,

    @ColumnInfo(name = "flag")
    var flag: String,

    @ColumnInfo(name = "capital")
    var capital: String,

    @ColumnInfo(name = "region")
    var region: String,

    @ColumnInfo(name = "callingCode")
    var callingCode: String,

    @ColumnInfo(name = "subRegion")
    var subRegion: String,

    @ColumnInfo(name = "language")
    var language: String,

    @ColumnInfo(name = "topLevelDomain")
    var topLevelDomain: String,

    @ColumnInfo(name = "currenciesCode")
    var currenciesCode: String?,

    @ColumnInfo(name = "currenciesName")
    var currenciesName: String?,

    @ColumnInfo(name = "currenciesSymbol")
    var currenciesSymbol: String?,

    @ColumnInfo(name = "timeZone")
    var timeZone: String,

    @ColumnInfo(name = "latitude")
    var latitude: Double,

    @ColumnInfo(name = "longitude")
    var longitude: Double,

    @ColumnInfo(name = "population")
    var population: Int,

    @ColumnInfo(name = "area")
    var area: Double,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)