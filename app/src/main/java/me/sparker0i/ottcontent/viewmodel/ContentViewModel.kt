package me.sparker0i.ottcontent.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.sparker0i.ottcontent.internal.deferred
import me.sparker0i.ottcontent.internal.lazyDeferred
import me.sparker0i.ottcontent.repository.ContentRepository

class ContentViewModel(
    private val contentRepository: ContentRepository
): ViewModel() {
    val countries by lazyDeferred {
        contentRepository.getCountries()
    }

    fun platforms(country: String) = deferred {
        contentRepository.getPlatforms(country)
    }

    val countryValue = MutableLiveData<String?>()
    val platformCountryValue = MutableLiveData<Pair<String, Int>?>()
}