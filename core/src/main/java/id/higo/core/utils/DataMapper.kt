package id.higo.core.utils

import id.higo.core.data.source.local.entity.CountryEntity
import id.higo.core.data.source.remote.response.CountryResponse
import id.higo.core.domain.model.Country

object DataMapper {

    fun mapResponsesToEntities(response: List<CountryResponse>): List<CountryEntity> {
        val countryList = ArrayList<CountryEntity>()
        var counter = 0
        response.map {
            counter++

            val latitude: Double
            val longitude: Double
            if (it.latlng != null && it.latlng.size > 1){
                latitude = it.latlng[0]
                longitude = it.latlng[1]
            }else{
                latitude = 0.0
                longitude = 0.0
            }

            val country = CountryEntity(
                countryId = counter.toString(),
                countryName = it.name,
                flag = it.flag,
                capital = it.capital,
                region = it.region,
                callingCode = it.callingCodes[0],
                subRegion = it.subregion,
                language = it.languages[0].name,
                topLevelDomain = it.topLevelDomain[0],
                currenciesCode = it.currencies?.get(0)?.code,
                currenciesName = it.currencies?.get(0)?.name,
                currenciesSymbol = it.currencies?.get(0)?.symbol,
                timeZone = it.timezones[0],
                latitude = latitude,
                longitude = longitude,
                population = it.population,
                area = it.area,
                isFavorite = false
            )
            countryList.add(country)
        }

        return countryList
    }

    fun mapEntitiesToDomain(input: List<CountryEntity>): List<Country> =
        input.map {
            Country(
                    it.countryId,
                    it.countryName,
                    it.flag,
                    it.capital,
                    it.region,
                    it.callingCode,
                    it.subRegion,
                    it.language,
                    it.topLevelDomain,
                    it.currenciesCode,
                    it.currenciesName,
                    it.currenciesSymbol,
                    it.timeZone,
                    it.latitude,
                    it.longitude,
                    it.population,
                    it.area,
                    it.isFavorite
            )
        }


    fun mapResponseToDomain(input: List<CountryResponse>): List<Country> {
        var result = ArrayList<Country>()
        var count = 0

        input.map {
            count++

            val latitude: Double
            val longitude: Double
            if (it.latlng != null && it.latlng.size > 1){
                latitude = it.latlng[0]
                longitude = it.latlng[1]
            }else{
                latitude = 0.0
                longitude = 0.0
            }

            val data = Country(
                    count.toString(),
                    it.name,
                    it.flag,
                    it.capital,
                    it.region,
                    it.callingCodes[0],
                    it.subregion,
                    it.languages[0].name,
                    it.topLevelDomain[0],
                    it.currencies?.get(0)?.code,
                    it.currencies?.get(0)?.name,
                    it.currencies?.get(0)?.symbol,
                    it.timezones[0],
                    latitude,
                    longitude,
                    it.population,
                    it.area,
                    false
            )
            result.add(data)
        }

        return result
    }


    fun mapDomainToEntity(input: Country): CountryEntity =
        CountryEntity(
            input.countryId,
            input.countryName,
            input.flag,
            input.capital,
            input.region,
            input.callingCode,
            input.subRegion,
            input.language,
            input.topLevelDomain,
            input.currenciesCode,
            input.currenciesName,
            input.currenciesSymbol,
            input.timeZone,
            input.latitude,
            input.longitude,
            input.population,
            input.area,
            input.isFavorite
        )
}