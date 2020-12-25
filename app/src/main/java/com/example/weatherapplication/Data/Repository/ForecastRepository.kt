package com.example.weatherapplication.Data.Repository

import androidx.lifecycle.LiveData
import com.example.weatherapplication.Data.db.Entity.WeatherLocation
import com.example.weatherapplication.Data.db.unitLocalized.Current.UnitSpecificCurrentWeatherEntry
import com.example.weatherapplication.Data.db.unitLocalized.Future.UnitSpecificSimpleFutureWeatherEntry
import org.threeten.bp.LocalDate

interface  ForecastRepository {
    suspend fun getCurrentWeather(metric:Boolean):LiveData<out UnitSpecificCurrentWeatherEntry>

    suspend fun getFutureWeatherList(startDate: LocalDate, metric: Boolean): LiveData<out List<UnitSpecificSimpleFutureWeatherEntry>>
//
//    suspend fun getFutureWeatherByDate(date:LocalDate, metric: Boolean): LiveData<out UnitSpecificDetailFutureWeatherEntry>


    suspend fun getWeatherLocation():LiveData<WeatherLocation>
}