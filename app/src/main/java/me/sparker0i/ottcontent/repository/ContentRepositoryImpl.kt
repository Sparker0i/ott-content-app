package me.sparker0i.ottcontent.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.sparker0i.ottcontent.db.ContentDao
import me.sparker0i.ottcontent.model.Country
import me.sparker0i.ottcontent.network.source.NetworkDataSource
import org.threeten.bp.ZonedDateTime

class ContentRepositoryImpl(
    private val contentDao: ContentDao,
    private val networkDataSource: NetworkDataSource
): ContentRepository {

    init {
        networkDataSource.getCountries.observeForever{newCountries ->
            persistFetchedCountries(newCountries)
        }
    }

    override suspend fun getCountries(): LiveData<List<Country>> {
        return withContext(Dispatchers.IO) {
            initCountries()
            return@withContext contentDao.getCountries()
        }
    }

    private fun persistFetchedCountries(fetchedCountries: List<Country>) {
        GlobalScope.launch(Dispatchers.IO) {
            fetchedCountries.forEach { country ->
                contentDao.insertCountry(country)
            }
        }
    }

    private suspend fun initCountries() {
        if (isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
            fetchCountries()
    }

    private suspend fun fetchCountries() {
        networkDataSource.fetchCountries()
    }

    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val hourAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(hourAgo)
    }
}