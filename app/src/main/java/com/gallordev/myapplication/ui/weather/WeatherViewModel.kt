package com.gallordev.myapplication.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gallordev.myapplication.model.Weather
import com.gallordev.myapplication.repository.WeatherRepository
import com.gallordev.myapplication.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _currentWeather = MutableLiveData<Resource<Weather>>(null)
    val currentWeather: LiveData<Resource<Weather>>
        get() = _currentWeather

    fun getCurrentWeather(location: String) {
        viewModelScope.launch {
            weatherRepository.getCurrentWeather(location).collect {
                _currentWeather.value = it
            }
        }
    }

}