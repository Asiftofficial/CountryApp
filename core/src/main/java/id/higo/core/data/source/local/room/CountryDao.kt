package id.higo.core.data.source.local.room

import androidx.room.*
import id.higo.core.data.source.local.entity.CountryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {

    @Query("SELECT * FROM countries")
    fun getAllCountry(): Flow<List<CountryEntity>>

    @Query("SELECT * FROM countries WHERE isFavorite = 1")
    fun getAllFavorite(): Flow<List<CountryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(country: List<CountryEntity>)

    @Update
    fun updateFavoriteCountry(country: CountryEntity)
}