package id.higo.countryapp

import android.app.Application
import id.higo.core.di.databaseModule
import id.higo.core.di.networkModule
import id.higo.core.di.repositoryModule
import id.higo.countryapp.di.useCaseModule
import id.higo.countryapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }

}