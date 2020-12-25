package com.example.weatherapplication.Data.network

import androidx.lifecycle.LiveData
import com.example.weatherapplication.Data.network.Response.CurrentWeatherResponse
import com.example.weatherapplication.Data.network.Response.FutureWeatherResponse

interface WeatherNetworkDataSource {
    val downloadCurrentWeather: LiveData<CurrentWeatherResponse>
    val downloadFutureWeather: LiveData<FutureWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String,
        languageCode:String
    )
    suspend fun fetchFutureWeather(
        location: String,
        languageCode:String
    )
}