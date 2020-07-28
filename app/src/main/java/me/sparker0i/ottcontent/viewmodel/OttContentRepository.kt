package me.sparker0i.ottcontent.viewmodel

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import me.sparker0i.ottcontent.model.Country
import me.sparker0i.ottcontent.network.RestApiService
import retrofit2.HttpException

class OttContentRepository() {
    private var mutableLiveData = MutableLiveData<List<Country>>()
    val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)

    private val thisApiService by lazy {
        RestApiService.createService()
    }

    fun getMutableLiveData(): MutableLiveData<List<Country>> {
        coroutineScope.launch {
            val request = thisApiService.getCountries()

            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    mutableLiveData.value = response
                }
                catch (e: HttpException) {

                }
                catch (e: Throwable) {

                }
            }
        }
        return mutableLiveData
    }
}