package com.example.weatherapplication.Data.network.Response


import com.example.weatherapplication.Data.db.Entity.FutureWeatherEntry
import com.google.gson.annotations.SerializedName

data class ForecastDaysContainer(
    @SerializedName("forecastday")
    val entries: List<FutureWeatherEntry>
)