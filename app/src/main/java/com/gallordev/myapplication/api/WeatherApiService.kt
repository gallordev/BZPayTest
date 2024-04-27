package com.gallordev.myapplication.api

import com.gallordev.myapplication.model.Weather
import com.gallordev.myapplication.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("key") apiKey: String = Constants.API_KEY,
        @Query("q") location: String
    ): Weather

}