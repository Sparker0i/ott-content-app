package me.sparker0i.ottcontent.network.source

import androidx.lifecycle.MutableLiveData
import me.sparker0i.ottcontent.model.Country
import me.sparker0i.ottcontent.model.Platform

interface NetworkDataSource {
    val getCountries: MutableLiveData<List<Country>>
    val getPlatforms: MutableLiveData<List<Platform>>

    suspend fun fetchCountries()
    suspend fun fetchPlatforms(country: String)
}