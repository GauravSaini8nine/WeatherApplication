package com.example.weatherapplication.Data.db.unitLocalized.Current

import org.threeten.bp.LocalDate

interface UnitSpecificCurrentWeatherEntry {
//    val date: LocalDate
    val day:Int
    val feelslike: Double
    val avgTemperature: Double
    val conditionText: String
    val conditionIconUrl: String
    val maxWindSpeed: Double
    val totalPrecipitation: Double
    val avgVisibilityDistance: Double
    val uv: Double
    val gust: Double
    val pressure: Double
    val lastUpdated: String
    val cloud: Int
    val windDirection:String
    val humidity: Int

//    val minimumTenp:
}