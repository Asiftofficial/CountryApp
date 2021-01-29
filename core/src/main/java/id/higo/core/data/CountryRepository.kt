package id.higo.core.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import id.higo.core.data.source.local.LocalDataSource
import id.higo.core.data.source.remote.RemoteDataSource
import id.higo.core.data.source.remote.network.ApiResponse
import id.higo.core.data.source.remote.response.CountryResponse
import id.higo.core.domain.model.Country
import id.higo.core.domain.repository.ICountryRepository
import id.higo.core.utils.AppExecutors
import id.higo.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class CountryRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors,
    private val context: Context
): ICountryRepository {
    override fun getAllCountry(): Flow<Resource<List<Country>>> =
        object : NetworkBoundResource<List<Country>,List<CountryResponse>>() {
            override fun loadFromDb(): Flow<List<Country>> {
                return localDataSource.getAllCountry().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetchData(data: List<Country>?): Boolean =
                    data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<CountryResponse>>> =
                remoteDataSource.getAllCountry()

            override suspend fun saveCallResult(data: List<CountryResponse>) {
                val countryList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertCountry(countryList)
            }

        }.asFlow()

    override fun getSearch(query: String): Flow<Resource<List<Country>>> {

        var result: Flow<Resource<List<Country>>> = flow {
            emit(Resource.Loading())

            val response = remoteDataSource.getSearch(query).first()

            when(response) {
                is ApiResponse.Error -> emit(Resource.Error<List<Country>>(response.message))
                is ApiResponse.Success -> emit(Resource.Success(DataMapper.mapResponseToDomain(response.data)))
            }
        }

        return result

    }



    override fun getFavoriteCountry(): Flow<List<Country>> =
        localDataSource.getAllFavorite().map {
            DataMapper.mapEntitiesToDomain(it)
        }

    override fun setFavoriteCountry(country: Country, state: Boolean) {
        val data = DataMapper.mapDomainToEntity(country)
        appExecutors.diskIO().execute { localDataSource.setFavorite(data,state)}
    }

    private fun isOnline(): Boolean {
        val con = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            val capabilities = con.getNetworkCapabilities(con.activeNetwork)

            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            return true
        }
        return false
    }
}