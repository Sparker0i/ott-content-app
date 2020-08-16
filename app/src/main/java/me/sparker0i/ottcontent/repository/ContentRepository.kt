package me.sparker0i.ottcontent.repository

import androidx.lifecycle.LiveData
import me.sparker0i.ottcontent.model.Country
import me.sparker0i.ottcontent.model.Platform

interface ContentRepository {
    suspend fun getCountries(): LiveData<List<Country>>
    suspend fun getPlatforms(country: String): LiveData<List<Platform>>
}