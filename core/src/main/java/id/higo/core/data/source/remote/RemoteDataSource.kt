package id.higo.core.data.source.remote

import id.higo.core.data.source.remote.network.ApiResponse
import id.higo.core.data.source.remote.network.ApiService
import id.higo.core.data.source.remote.response.CountryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllCountry(): Flow<ApiResponse<List<CountryResponse>>> {

        return flow {
            try {
                val response = apiService.getAllCountry()
                if (response.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                }else {
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                e.printStackTrace()
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSearch(query: String): Flow<ApiResponse<List<CountryResponse>>> {
        return flow {
            try {
                val response = apiService.getSearch(query)
                if (response.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                }else {
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                e.printStackTrace()
            }
        }.flowOn(Dispatchers.IO)
    }
}