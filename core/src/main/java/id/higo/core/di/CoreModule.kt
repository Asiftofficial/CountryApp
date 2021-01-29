package id.higo.core.di

import androidx.room.Room
import id.higo.core.data.CountryRepository
import id.higo.core.data.source.local.LocalDataSource
import id.higo.core.data.source.local.room.CountryDatabase
import id.higo.core.data.source.remote.RemoteDataSource
import id.higo.core.data.source.remote.network.ApiService
import id.higo.core.domain.repository.ICountryRepository
import id.higo.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<CountryDatabase>().countryDao()}

    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("asift".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
        CountryDatabase::class.java,
        "Country.db").fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {

    single {

        val hostname = "restcountries.eu"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
            .add(hostname, "sha256/wxgZ6Jx5WaNt5zAgUSDnLsK8E5uy+DUumAogHk4P7R8=")
            .add(hostname, "sha256/Y9mvm0exBk1JoQ57f9Vm28jKo5lFm/woKcVxrYxu80o=")
            .build()


        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120,TimeUnit.SECONDS)
            .readTimeout(120,TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.eu/rest/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()

        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ICountryRepository> {
        CountryRepository(
            get(),
            get(),
            get(),
            androidContext()
        )
    }
}
