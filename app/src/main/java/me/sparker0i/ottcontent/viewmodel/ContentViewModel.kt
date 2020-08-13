package me.sparker0i.ottcontent.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.sparker0i.ottcontent.internal.lazyDeferred
import me.sparker0i.ottcontent.repository.ContentRepository

class ContentViewModel(
    private val contentRepository: ContentRepository
): ViewModel() {
    val countries by lazyDeferred {
        contentRepository.getCountries()
    }

    val countryValue = MutableLiveData<String?>()
}