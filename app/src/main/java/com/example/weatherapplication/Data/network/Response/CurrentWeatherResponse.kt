package com.example.weatherapplication.Data.network.Response

import com.example.weatherapplication.Data.db.Entity.CurrentWeatherEntry
import com.example.weatherapplication.Data.db.Entity.WeatherLocation
import com.google.gson.annotations.SerializedName


data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: WeatherLocation
)