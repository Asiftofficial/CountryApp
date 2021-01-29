package id.higo.countryapp.di

import id.higo.core.domain.usecase.CountryInteractor
import id.higo.core.domain.usecase.CountryUseCase
import id.higo.countryapp.ui.detail.DetailViewModel
import id.higo.countryapp.ui.home.HomeViewModel
import id.higo.countryapp.ui.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<CountryUseCase> { CountryInteractor(get())}
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}