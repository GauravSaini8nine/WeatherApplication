package com.example.weatherapplication.Data.db.unitLocalized.Current

import androidx.room.ColumnInfo


data class MetricCurrentWeatherEntry(
//    override val date: LocalDate,

    @ColumnInfo(name = "feelslikeC")
    override val feelslike: Double,

//    @SerializedName("")
//    override val maxTemperature: Double,
//
//    @SerializedName("feelslikeC")
//    override val minTemperature: Double,

    @ColumnInfo(name = "tempC")
    override val avgTemperature: Double,

    @ColumnInfo(name = "condition_text")
    override val conditionText: String,

    @ColumnInfo(name = "condition_icon")
    override val conditionIconUrl: String,

    @ColumnInfo(name = "windKph")
    override val maxWindSpeed: Double,

    @ColumnInfo(name = "precipMm")
    override val totalPrecipitation: Double,

    @ColumnInfo(name = "visKm")
    override val avgVisibilityDistance: Double,


    @ColumnInfo(name = "gustKph")
    override val gust: Double,

    @ColumnInfo(name = "pressureMb")
    override val pressure: Double,


    @ColumnInfo(name = "windDir")
    override val windDirection: String,

    @ColumnInfo(name = "isDay")
    override val day: Int,


    override val lastUpdated: String,
    override val cloud: Int,
    override val humidity: Int,
    override val uv: Double,


//    override val temperature: Double,
//    @ColumnInfo(name = "condition_text")
//    override val conditionText: String,
//    @ColumnInfo(name = "condition_icon")
//    override val conditionIconUrl: String,
//    @ColumnInfo(name = "windKph")
//    override val windSpeed: Double,
//    @ColumnInfo(name = "windDir")
//    override val windDirection: String,
//    @ColumnInfo(name = "precipMm")
//    override val precipitationVolume: Double,
//    @ColumnInfo(name = "feelslikeC")
//    override val feelsLikeTemperature: Double,
//    @ColumnInfo(name = "visKm")
//    override val visibilityDistance: Double
) : UnitSpecificCurrentWeatherEntry