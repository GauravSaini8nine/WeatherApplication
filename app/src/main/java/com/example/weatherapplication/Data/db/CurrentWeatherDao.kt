package com.example.weatherapplication.Data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapplication.Data.db.Entity.CURRENT_WEATHER_ID
import com.example.weatherapplication.Data.db.Entity.CurrentWeatherEntry
import com.example.weatherapplication.Data.db.unitLocalized.Current.ImperialCurrentWeatherEntry
import com.example.weatherapplication.Data.db.unitLocalized.Current.MetricCurrentWeatherEntry

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry:CurrentWeatherEntry)


    @Query("select * from current_weather where id = $CURRENT_WEATHER_ID")
    fun getWeatherMetric():LiveData<MetricCurrentWeatherEntry>



    @Query("select * from current_weather where id = $CURRENT_WEATHER_ID")
    fun getWeatherImperial():LiveData<ImperialCurrentWeatherEntry>

}