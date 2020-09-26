package ir.kaaveh.urmanopenweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.kaaveh.urmanopenweather.model.WeatherResponse
import ir.kaaveh.urmanopenweather.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    private var _weather = MutableLiveData<WeatherResponse>()

    init {
        viewModelScope.launch {
            _weather = weatherRepository.getCurrentWeather() as MutableLiveData<WeatherResponse>
        }
    }

    val weather: LiveData<WeatherResponse>
        get() = _weather
}