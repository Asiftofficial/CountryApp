package id.higo.core.domain.usecase

import id.higo.core.data.Resource
import id.higo.core.domain.model.Country
import kotlinx.coroutines.flow.Flow

interface CountryUseCase {

    fun getAllCountry(): Flow<Resource<List<Country>>>

    fun getSearch(query: String): Flow<Resource<List<Country>>>

    fun getAllFavorite(): Flow<List<Country>>

    fun setFavorite(country: Country, state: Boolean)
}