package com.example.weather.ui.weather.future.list
//
//import android.os.Build
//import androidx.annotation.RequiresApi
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.weatherapplication.Data.Repository.ForecastRepository
//import com.example.weatherapplication.Data.provider.UnitProvider
//
//class FutureListWeatherViewModelFactory(
//    private val forecastRepository: ForecastRepository,
//    private val unitProvider: UnitProvider
//) : ViewModelProvider.NewInstanceFactory() {
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return FutureListWeatherViewModel(
//            forecastRepository,
//            unitProvider
//        ) as T
//    }
//}