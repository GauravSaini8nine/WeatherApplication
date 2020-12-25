package com.example.weatherapplication.Data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapplication.Data.Internal.NoConnectivityException
import com.example.weatherapplication.Data.network.Response.CurrentWeatherResponse
import com.example.weatherapplication.Data.network.Response.FutureWeatherResponse


const val FORCAST_DAYS_COUNT= 7


class WeatherNetworkDataSourceImpl(
    private val api_service: ApiService
) : WeatherNetworkDataSource{

    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()

    override val downloadCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String, languageCode: String) {
        try {
            val fetchedCurrentWeather = api_service
                .getCurrentWeather(location, languageCode)
                .await()
            _downloadedCurrentWeather.postValue(fetchedCurrentWeather)
        }
        catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }



    private val _downloadedFutureWeather = MutableLiveData<FutureWeatherResponse>()
    override val downloadFutureWeather: LiveData<FutureWeatherResponse>
        get() = _downloadedFutureWeather

    override suspend fun fetchFutureWeather(
        location: String,
        languageCode: String
    ) {
        try {
            val fetchedFutureWeather = api_service
                .getFutureWeather(location, FORCAST_DAYS_COUNT, languageCode)
                .await()
            _downloadedFutureWeather.postValue(fetchedFutureWeather)
        }
        catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }
}