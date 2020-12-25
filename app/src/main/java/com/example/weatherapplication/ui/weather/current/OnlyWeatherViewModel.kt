package com.example.weatherapplication.ui.weather.current

import androidx.lifecycle.ViewModel
import com.example.weatherapplication.Data.Internal.UnitSystem
import com.example.weatherapplication.Data.Internal.lazyDeferred
import com.example.weatherapplication.Data.Repository.ForecastRepository
import com.example.weatherapplication.Data.provider.UnitProvider
import org.threeten.bp.LocalDate

class OnlyWeatherViewModel (

    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
        ): ViewModel() {
    private val unitSystem= unitProvider.getUnitSystem()
    val isMetric: Boolean
    get() = unitSystem ==UnitSystem.METRIC

//  val weather = forecastRepository.getCurrentWeather(isMetric)

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather( isMetric)
    }

    val weatherLocation by lazyDeferred {
        forecastRepository.getWeatherLocation()
    }


    val weatherEntries by lazyDeferred {
        forecastRepository.getFutureWeatherList(LocalDate.now(), isMetric)
    }
}