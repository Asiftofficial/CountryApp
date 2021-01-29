package id.higo.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.higo.core.domain.usecase.CountryUseCase

class FavoriteViewModel(countryUseCase: CountryUseCase) : ViewModel() {

    val favorite = countryUseCase.getAllFavorite().asLiveData()
}