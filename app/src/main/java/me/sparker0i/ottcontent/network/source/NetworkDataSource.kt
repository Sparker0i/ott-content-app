package me.sparker0i.ottcontent.network.source

import androidx.lifecycle.MutableLiveData
import me.sparker0i.ottcontent.model.Country

interface NetworkDataSource {
    val getCountries: MutableLiveData<List<Country>>

    suspend fun fetchCountries()
}