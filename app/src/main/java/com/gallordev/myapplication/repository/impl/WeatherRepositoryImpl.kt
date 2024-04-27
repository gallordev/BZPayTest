package com.gallordev.myapplication.repository.impl

import com.gallordev.myapplication.api.WeatherApiService
import com.gallordev.myapplication.model.Weather
import com.gallordev.myapplication.repository.WeatherRepository
import com.gallordev.myapplication.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: WeatherApiService
): WeatherRepository {

    override suspend fun getCurrentWeather(location: String): Flow<Resource<Weather>> = flow {
        emit(Resource.Loading())
        val result = apiService.getCurrentWeather(location = location)
        emit(Resource.Success(result))
    }.catch {
        emit(Resource.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

}