package com.example.weatherapplication.Data.db.Entity


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.weatherapplication.Data.network.Response.Astro


@Entity(tableName = "future_weather" , indices = [Index(value = ["date"],unique = true) ])
data class FutureWeatherEntry(

//    val astro: Astro,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val date: String,

    @Embedded
    val day: Day
//    val hour: List<Hour>
)