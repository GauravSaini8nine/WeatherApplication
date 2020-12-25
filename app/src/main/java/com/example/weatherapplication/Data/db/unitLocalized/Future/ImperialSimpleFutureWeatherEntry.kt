package com.resocoder.forecastmvvm.data.db.unitlocalized.future.list

import androidx.room.ColumnInfo
import com.example.weatherapplication.Data.db.unitLocalized.Future.UnitSpecificSimpleFutureWeatherEntry
import org.threeten.bp.LocalDate


class ImperialSimpleFutureWeatherEntry (
    @ColumnInfo(name = "date")
    override val date: LocalDate,
    @ColumnInfo(name = "avgtempF")
    override val avgTemperature: Double,
    @ColumnInfo(name = "condition_text")
    override val conditionText: String,
    @ColumnInfo(name = "condition_icon")
    override val conditionIconUrl: String,
    @ColumnInfo(name = "maxtempF")
    override val maxtemp: Double,
    @ColumnInfo(name = "mintempF")
    override val mintemp: Double,


    override val uv: Double,
    override val dailyChanceOfSnow: String,
    override val dailyChanceOfRain: String,
    override val avghumidity: Double,
    @ColumnInfo(name = "maxwindMph")
    override val maxwind: Double
) : UnitSpecificSimpleFutureWeatherEntry