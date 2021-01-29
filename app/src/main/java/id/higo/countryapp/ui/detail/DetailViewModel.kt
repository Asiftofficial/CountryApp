package id.higo.countryapp.ui.detail

import androidx.lifecycle.ViewModel
import id.higo.core.domain.model.Country
import id.higo.core.domain.usecase.CountryUseCase

class DetailViewModel(private val countryUseCase: CountryUseCase): ViewModel() {

    fun setFavorite(data: Country, state: Boolean) = countryUseCase.setFavorite(data,state)
}