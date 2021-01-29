package id.higo.core.data.source.remote.network

import id.higo.core.data.source.remote.response.CountryResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("all")
    suspend fun getAllCountry(): List<CountryResponse>

    @GET("name/{query}")
    suspend fun getSearch(
            @Path("query") query: String
    ): List<CountryResponse>
}