package me.sparker0i.ottcontent.repository

import androidx.lifecycle.LiveData
import me.sparker0i.ottcontent.model.Country

interface ContentRepository {
    suspend fun getCountries(): LiveData<List<Country>>
}