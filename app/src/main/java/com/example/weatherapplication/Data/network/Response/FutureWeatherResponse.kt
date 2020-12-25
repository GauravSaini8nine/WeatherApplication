package com.example.weatherapplication.Data.network.Response


import com.example.weatherapplication.Data.db.Entity.WeatherLocation
import com.google.gson.annotations.SerializedName

data class FutureWeatherResponse(

    @SerializedName( "forecast")
    val futureWeatherEntries: ForecastDaysContainer,
    val location: WeatherLocation
)