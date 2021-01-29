package id.higo.countryapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.higo.core.domain.usecase.CountryUseCase

class HomeViewModel(countryUseCase: CountryUseCase) : ViewModel() {
    val country = countryUseCase.getAllCountry().asLiveData()
}