package com.example.weatherapplication.Data.provider

import com.example.weatherapplication.Data.Internal.UnitSystem

interface UnitProvider {

    fun getUnitSystem(): UnitSystem
}