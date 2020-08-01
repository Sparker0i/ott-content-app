package me.sparker0i.ottcontent.network.source

import android.util.Log
import androidx.lifecycle.MutableLiveData
import me.sparker0i.ottcontent.internal.NoConnectivityException
import me.sparker0i.ottcontent.model.Country
import me.sparker0i.ottcontent.network.RestApiService

class NetworkDataSourceImpl(
    private val restApiService: RestApiService
): NetworkDataSource {
    override val getCountries = MutableLiveData<List<Country>>()

    override suspend fun fetchCountries() {
        try {
            val countries = restApiService
                .getCountries()
                .await()

            getCountries.postValue(countries)
        }
        catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No Internet Connection", e)
        }
    }
}