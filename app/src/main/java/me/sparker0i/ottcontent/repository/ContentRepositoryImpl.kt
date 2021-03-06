package me.sparker0i.ottcontent.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.sparker0i.ottcontent.db.ContentDao
import me.sparker0i.ottcontent.model.Country
import me.sparker0i.ottcontent.model.Platform
import me.sparker0i.ottcontent.network.source.NetworkDataSource

class ContentRepositoryImpl(
    private val contentDao: ContentDao,
    private val networkDataSource: NetworkDataSource
): ContentRepository {
    init {
        networkDataSource.getCountries.observeForever{newCountries ->
            persistFetchedCountries(newCountries)
        }

        networkDataSource.getPlatforms.observeForever{newPlatforms ->
            persistFetchedPlatforms(newPlatforms)
        }
    }

    override suspend fun getCountries(): LiveData<List<Country>> {
        return withContext(Dispatchers.IO) {
            fetchCountries()
            return@withContext contentDao.getCountries()
        }
    }

    override suspend fun getPlatforms(country: String): LiveData<List<Platform>> {
        return withContext(Dispatchers.IO) {
            fetchPlatforms(country)
            return@withContext contentDao.getPlatforms(country)
        }
    }

    private fun persistFetchedCountries(fetchedCountries: List<Country>) {
        if (fetchedCountries.isNotEmpty())
            GlobalScope.launch(Dispatchers.IO) {
                contentDao.deleteNonExistingCountries(fetchedCountries.map { country -> country.code })
                contentDao.insertCountries(fetchedCountries)
            }
    }

    private fun persistFetchedPlatforms(fetchedPlatforms: List<Platform>) {
        if (fetchedPlatforms.isNotEmpty())
            GlobalScope.launch(Dispatchers.IO) {
                contentDao.deleteNonExistingPlatformsForCountry(fetchedPlatforms[0].countryCode, fetchedPlatforms.map { platform -> platform.platformId })
                contentDao.insertPlatforms(fetchedPlatforms)
            }
    }

    private suspend fun fetchPlatforms(country: String) {
        networkDataSource.fetchPlatforms(country)
    }

    private suspend fun fetchCountries() {
        networkDataSource.fetchCountries()
    }
}