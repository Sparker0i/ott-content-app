package me.sparker0i.ottcontent.network.interceptor

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import me.sparker0i.ottcontent.internal.exception.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptorImpl(
    context: Context
) : ConnectivityInterceptor {

    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline())
            throw NoConnectivityException()
        return chain.proceed(chain.request())
    }

    private fun isOnline(): Boolean {
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetworks = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetworks.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetworks.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetworks.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            activeNetworks.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    }
}