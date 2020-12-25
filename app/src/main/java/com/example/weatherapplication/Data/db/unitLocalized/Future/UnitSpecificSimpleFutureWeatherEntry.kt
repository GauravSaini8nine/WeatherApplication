package com.example.weatherapplication.Data.db.unitLocalized.Future

import org.threeten.bp.LocalDate

interface UnitSpecificSimpleFutureWeatherEntry {

    val date:LocalDate
    val avgTemperature:Double
    val conditionText:String
    val conditionIconUrl:String
    val maxtemp: Double
    val mintemp: Double
    val uv: Double
    val dailyChanceOfSnow: String
    val dailyChanceOfRain: String
    val avghumidity: Double
    val maxwind: Double

}