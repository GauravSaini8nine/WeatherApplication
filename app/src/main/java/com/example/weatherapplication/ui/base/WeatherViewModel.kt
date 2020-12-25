package com.example.weatherapplication.ui.base

import androidx.lifecycle.ViewModel

import com.example.weatherapplication.Data.Internal.UnitSystem
import com.example.weatherapplication.Data.Internal.lazyDeferred
import com.example.weatherapplication.Data.Repository.ForecastRepository
import com.example.weatherapplication.Data.provider.UnitProvider

abstract class WeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : ViewModel() {

    private val unitSystem = unitProvider.getUnitSystem()

    val isMetricUnit: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weatherLocation by lazyDeferred {
        forecastRepository.getWeatherLocation()
    }
}