package com.example.weatherapplication.Data.db.unitLocalized.Current

import androidx.room.ColumnInfo


data class ImperialCurrentWeatherEntry(
//    @ColumnInfo(name = "date")
//    override val date: LocalDate,

    @ColumnInfo(name = "feelslikeF")
    override val feelslike: Double,


//    override val maxTemperature: Double,
//    override val minTemperature: Double,
    @ColumnInfo(name = "tempF")
    override val avgTemperature: Double,


    @ColumnInfo(name = "condition_text")
    override val conditionText: String,


    @ColumnInfo(name = "condition_icon")
    override val conditionIconUrl: String,


    @ColumnInfo(name = "windMph")
    override val maxWindSpeed: Double,


    @ColumnInfo(name = "precipIn")
    override val totalPrecipitation: Double,


    @ColumnInfo(name = "visMiles")
    override val avgVisibilityDistance: Double,


    @ColumnInfo(name = "gustMph")
    override val gust: Double,


    @ColumnInfo(name = "pressureIn")
    override val pressure: Double,


    @ColumnInfo(name = "windDir")
    override val windDirection: String,

    @ColumnInfo(name = "isDay")
    override val day: Int,



    override val uv: Double,
    override val lastUpdated: String,
    override val cloud: Int,
    override val humidity: Int,




//    @ColumnInfo(name = "tempF")
//    override val temperature: Double,
//    @ColumnInfo(name = "condition_text")
//    override val conditionText: String,
//    @ColumnInfo(name = "condition_icon")
//    override val conditionIconUrl: String,
//    @ColumnInfo(name = "windMph")
//    override val windSpeed: Double,
//    @ColumnInfo(name = "windDir")
//    override val windDirection: String,
//    @ColumnInfo(name = "precipIn")
//    override val precipitationVolume: Double,
//    @ColumnInfo(name = "feelslikeF")
//    override val feelsLikeTemperature: Double,
//    @ColumnInfo(name = "visMiles")
//    override val visibilityDistance: Double
) : UnitSpecificCurrentWeatherEntry