package com.example.weatherapplication

import android.app.Application
import android.content.Context
import androidx.preference.PreferenceManager
import com.example.weatherapplication.Data.Repository.ForecastRepository
import com.example.weatherapplication.Data.Repository.ForecastRepositoryImpl
import com.example.weatherapplication.Data.db.ForecastDatabase
import com.example.weatherapplication.Data.network.*
import com.example.weatherapplication.Data.provider.LocationProvider
import com.example.weatherapplication.Data.provider.LocationProviderImpl
import com.example.weatherapplication.Data.provider.UnitProvider
import com.example.weatherapplication.Data.provider.UnitProviderImpl
import com.example.weatherapplication.ui.weather.current.OnlyWeatherViewModelFactory
import com.google.android.gms.location.LocationServices
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ForecatsApplication: Application(), KodeinAware {
    override val kodein= Kodein.lazy {
        import( androidXModule(this@ForecatsApplication))

        bind() from singleton { ForecastDatabase(instance()) }
        bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }
        bind() from singleton { instance<ForecastDatabase>().futureWeatherDao() }



//        location Part
        bind() from singleton { instance<ForecastDatabase>().weatherLocationDao() }


        bind<ConnectivityInterceptor>()  with singleton { ConnectivityInterceptorImpl(instance())}

//        binding apiService
        bind() from singleton { ApiService(instance()) }

        bind<WeatherNetworkDataSource>()  with singleton { WeatherNetworkDataSourceImpl(instance())}
        bind()  from provider { LocationServices.getFusedLocationProviderClient(instance<Context>()) }
//        Location part
        bind<LocationProvider>() with singleton { LocationProviderImpl(instance(), instance()) }

        bind<ForecastRepository>()  with singleton { ForecastRepositoryImpl(instance(),instance(),instance(),instance(), instance())}
        bind<UnitProvider>()with singleton { UnitProviderImpl(instance()) }

        bind() from provider { OnlyWeatherViewModelFactory(instance(), instance()) }

    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
    }

}