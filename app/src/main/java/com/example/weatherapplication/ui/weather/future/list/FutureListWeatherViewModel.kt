package com.example.weather.ui.weather.future.list
//
//import android.os.Build
//import androidx.annotation.RequiresApi
//
//import com.example.weatherapplication.Data.Internal.lazyDeferred
//import com.example.weatherapplication.Data.Repository.ForecastRepository
//import com.example.weatherapplication.Data.provider.UnitProvider
//import com.example.weatherapplication.ui.base.WeatherViewModel
//import org.threeten.bp.LocalDate
//
//
//@RequiresApi(Build.VERSION_CODES.O)
//class FutureListWeatherViewModel (
//    private val forecastRepository: ForecastRepository,
//    unitProvider: UnitProvider
//    ): WeatherViewModel(forecastRepository, unitProvider){
//
//    val weatherEntries by lazyDeferred {
//        forecastRepository.getFutureWeatherList(LocalDate.now(), super.isMetricUnit)
//
//    }
//    }