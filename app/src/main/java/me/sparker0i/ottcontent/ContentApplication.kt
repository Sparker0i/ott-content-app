package me.sparker0i.ottcontent

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import me.sparker0i.ottcontent.db.ContentDatabase
import me.sparker0i.ottcontent.network.RestApiService
import me.sparker0i.ottcontent.network.source.NetworkDataSource
import me.sparker0i.ottcontent.network.interceptor.ConnectivityInterceptor
import me.sparker0i.ottcontent.network.interceptor.ConnectivityInterceptorImpl
import me.sparker0i.ottcontent.network.source.NetworkDataSourceImpl
import me.sparker0i.ottcontent.repository.ContentRepository
import me.sparker0i.ottcontent.repository.ContentRepositoryImpl
import me.sparker0i.ottcontent.viewmodel.ContentViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ContentApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ContentApplication))

        bind() from singleton { ContentDatabase(instance()) }
        bind() from singleton { instance<ContentDatabase>().contentDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { RestApiService(instance()) }
        bind<NetworkDataSource>() with singleton { NetworkDataSourceImpl(instance()) }
        bind<ContentRepository>() with singleton { ContentRepositoryImpl(instance(), instance()) }
        bind() from provider { ContentViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}