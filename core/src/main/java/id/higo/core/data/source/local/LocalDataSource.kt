package id.higo.core.data.source.local

import id.higo.core.data.source.local.entity.CountryEntity
import id.higo.core.data.source.local.room.CountryDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val countryDao: CountryDao) {

    fun getAllCountry(): Flow<List<CountryEntity>> = countryDao.getAllCountry()

    fun getAllFavorite(): Flow<List<CountryEntity>> = countryDao.getAllFavorite()

    suspend fun insertCountry(countryList: List<CountryEntity>) = countryDao.insertCountry(countryList)

    fun setFavorite(country: CountryEntity, newState: Boolean){
        country.isFavorite = newState
        countryDao.updateFavoriteCountry(country)
    }

}