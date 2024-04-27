package com.gallordev.myapplication.repository

import com.gallordev.myapplication.model.Weather
import com.gallordev.myapplication.utils.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getCurrentWeather(location: String): Flow<Resource<Weather>>
}