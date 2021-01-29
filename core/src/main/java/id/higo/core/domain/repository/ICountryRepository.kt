package id.higo.core.domain.repository

import id.higo.core.data.Resource
import id.higo.core.domain.model.Country
import kotlinx.coroutines.flow.Flow

interface ICountryRepository {

    fun getAllCountry(): Flow<Resource<List<Country>>>

    fun getSearch(query: String): Flow<Resource<List<Country>>>

    fun getFavoriteCountry(): Flow<List<Country>>

    fun setFavoriteCountry(country: Country, state: Boolean)
}