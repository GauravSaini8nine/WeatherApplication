package com.example.weatherapplication.Data.network

import com.example.weatherapplication.Data.network.Response.CurrentWeatherResponse
import com.example.weatherapplication.Data.network.Response.FutureWeatherResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_Key= "15dddc44c90b4965b8245453201912"  ///mine
//const val API_Key= "89e8bd89085b41b7a4b142029180210"

//http://api.weatherapi.com/v1/current.json?key=15dddc44c90b4965b8245453201912&q=india&lang=en



interface ApiService {
    @GET("current.json")
    fun getCurrentWeather(
        @Query("q") location: String,
        @Query("lang") languageCode:String = "en"
    ):Deferred<CurrentWeatherResponse>



    ///http://api.weatherapi.com/v1/forecast.json?key=15dddc44c90b4965b8245453201912&q=india&days=1
    @GET("forecast.json")
    fun getFutureWeather(
        @Query("q") location: String,
        @Query("days") days:Int,
        @Query("lang") languageCode:String = "en"
    ):Deferred<FutureWeatherResponse>











    companion object{
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ):ApiService{
            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key", API_Key)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
//                .baseUrl("http://api.apixu.com/v1/")
                .baseUrl("http://api.weatherapi.com/v1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
        }
    }
