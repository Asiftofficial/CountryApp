package id.higo.core.data

import id.higo.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())

        val dbSource = loadFromDb().first()

        if (shouldFetchData(dbSource)){

            emit(Resource.Loading())

            val response = createCall().first()

            when(response) {
                is ApiResponse.Success -> {
                    saveCallResult(response.data)
                    emitAll(loadFromDb().map {
                        Resource.Success(it)
                    })
                }

                is ApiResponse.Empty -> {
                    emitAll(loadFromDb().map {
                        Resource.Success(it)
                    })
                }

                is ApiResponse.Error -> {
                    emit(Resource.Error<ResultType>(response.message))
                }
            }
        } else {
            emitAll(loadFromDb().map {
                Resource.Success(it)
            })
        }
    }

    protected abstract fun loadFromDb(): Flow<ResultType>

    protected abstract fun shouldFetchData(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}