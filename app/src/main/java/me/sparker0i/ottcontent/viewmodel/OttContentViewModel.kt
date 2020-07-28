package me.sparker0i.ottcontent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import me.sparker0i.ottcontent.model.Country

class OttContentViewModel(): ViewModel() {
    val ottContentRepository = OttContentRepository()
    val countries: LiveData<List<Country>> get() = ottContentRepository.getMutableLiveData()

    override fun onCleared() {
        super.onCleared()
        ottContentRepository.completableJob.cancel()
    }
}