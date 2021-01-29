package id.higo.countryapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.higo.core.domain.usecase.CountryUseCase

class SearchViewModel(private val countryUseCase: CountryUseCase) : ViewModel() {

    fun search(query: String) = countryUseCase.getSearch(query).asLiveData()
}