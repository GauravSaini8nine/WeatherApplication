package com.example.weatherapplication.Data.Internal

import java.io.IOException
import java.lang.Exception

class NoConnectivityException:IOException()
class LocationPermissionNotGrantedException: Exception()