package id.higo.core.domain.usecase

import id.higo.core.data.Resource
import id.higo.core.domain.model.Country
import id.higo.core.domain.repository.ICountryRepository
import kotlinx.coroutines.flow.Flow

class CountryInteractor(private val countryRepository: ICountryRepository): CountryUseCase {
    override fun getAllCountry(): Flow<Resource<List<Country>>> = countryRepository.getAllCountry()

    override fun getSearch(query: String): Flow<Resource<List<Country>>> = countryRepository.getSearch(query)

    override fun getAllFavorite(): Flow<List<Country>> = countryRepository.getFavoriteCountry()

    override fun setFavorite(country: Country, state: Boolean) = countryRepository.setFavoriteCountry(country,state)
}