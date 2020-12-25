package com.example.weatherapplication.Data.provider

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

const val USE_DEVICE_LOCATION= "USE_DEVICE_LOCATION"
const val CUSTOM_LOCATION= "CUSTOM_LOCATION"

abstract class PreferenceProvider(context: Context) {


    private val appContext = context.applicationContext


    protected val preferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)
}