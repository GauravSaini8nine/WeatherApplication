package com.example.weatherapplication.Data.provider

import com.example.weatherapplication.Data.db.Entity.WeatherLocation

interface LocationProvider {
    suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean
    suspend fun getPreferredLocationString(): String
}