package com.example.weatherapplication.Data.Repository

import androidx.lifecycle.LiveData
import com.example.weatherapplication.Data.db.CurrentWeatherDao
import com.example.weatherapplication.Data.db.Entity.WeatherLocation
import com.example.weatherapplication.Data.db.FutureWeatherDao
import com.example.weatherapplication.Data.db.WeatherLocationDao
import com.example.weatherapplication.Data.db.unitLocalized.Current.UnitSpecificCurrentWeatherEntry
import com.example.weatherapplication.Data.db.unitLocalized.Future.UnitSpecificSimpleFutureWeatherEntry
import com.example.weatherapplication.Data.network.FORCAST_DAYS_COUNT
import com.example.weatherapplication.Data.network.Response.CurrentWeatherResponse
import com.example.weatherapplication.Data.network.Response.FutureWeatherResponse
import com.example.weatherapplication.Data.network.WeatherNetworkDataSource
import com.example.weatherapplication.Data.provider.LocationProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import org.threeten.bp.ZonedDateTime
import java.util.*

class ForecastRepositoryImpl(
    private val currentWeatherDao:CurrentWeatherDao,
    private val futureWeatherDao: FutureWeatherDao,
    private val weatherLocationDao: WeatherLocationDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource,
    private val locationProvider: LocationProvider
) : ForecastRepository {
        init {
            weatherNetworkDataSource.apply {
                downloadCurrentWeather.observeForever { newCurrentWeather->
                    persistFetchedCurrentWeather(newCurrentWeather)
            }
                downloadFutureWeather.observeForever { newFutureWeather ->
                    persistFetchedFutureWeather(newFutureWeather)
                }
            }
        }



    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry> {
        return withContext(Dispatchers.IO){
            initWeatherData()
            return@withContext if (metric) currentWeatherDao.getWeatherMetric()
            else currentWeatherDao.getWeatherImperial()

        }
    }


    override suspend fun getFutureWeatherList(
        startDate: LocalDate,
        metric: Boolean
    ): LiveData<out List<UnitSpecificSimpleFutureWeatherEntry>> {
            return withContext(Dispatchers.IO){
                initWeatherData()
                return@withContext if(metric) futureWeatherDao.getSimpleWeatherForecastsMetric(startDate)
                else futureWeatherDao.getSimpleWeatherForecastsImperial(startDate)
            }
    }




    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse){
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)
//            Location Part
            weatherLocationDao.upsert(fetchedWeather.location)
        }

    }

    private fun persistFetchedFutureWeather(fetchedWeather: FutureWeatherResponse){
        fun deleteOldForecastData(){
            val today= LocalDate.now()
            futureWeatherDao.deleteOldEntries((today))
        }

        GlobalScope.launch(Dispatchers.IO) {
            deleteOldForecastData()
            val futureWeatherList= fetchedWeather.futureWeatherEntries.entries
            futureWeatherDao.insert(futureWeatherList)
            weatherLocationDao.upsert(fetchedWeather.location)
        }


    }




    private suspend fun initWeatherData(){
//        Location Part
            val lastWeatherLocation= weatherLocationDao.getLocationNonLive()

        if (lastWeatherLocation == null
            || locationProvider.hasLocationChanged(lastWeatherLocation)){
            fetchCurrentWeather()
            fetchFutureWeather()
            return
        }

        if (isFetchCurrentNeended(lastWeatherLocation.zonedDateTime))
            fetchCurrentWeather()

        if (isFetchFutureNeended(lastWeatherLocation.zonedDateTime))
            fetchFutureWeather()
    }
    private suspend fun fetchCurrentWeather (){
        weatherNetworkDataSource.fetchCurrentWeather(
            locationProvider.getPreferredLocationString(),
            Locale.getDefault().language
        )
    }


    private suspend fun fetchFutureWeather (){
        weatherNetworkDataSource.fetchFutureWeather(
            locationProvider.getPreferredLocationString(),
            Locale.getDefault().language
        )
    }




    private fun isFetchCurrentNeended(lastFetchTime: ZonedDateTime):Boolean{
        val thirtyMinutesAgo= ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }

    private fun isFetchFutureNeended(lastFetchTime: ZonedDateTime):Boolean{
        val today= LocalDate.now()
        val futureWeatherCount = futureWeatherDao.countFutureWeather(today)
        return futureWeatherCount< FORCAST_DAYS_COUNT
    }





//    Location Part Of The Project

    override suspend fun getWeatherLocation(): LiveData<WeatherLocation> {
        return withContext(Dispatchers.IO){
            return@withContext weatherLocationDao.getLocation()
        }
    }




}