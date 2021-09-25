package com.skrebtsov.eugeney.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skrebtsov.eugeney.weather.model.models.firstapi.DataWeatherCity
import com.skrebtsov.eugeney.weather.repository.RepositoryWeather
import javax.inject.Inject

class WeatherByMinskViewModel @Inject constructor(private val repositoryWeather: RepositoryWeather): ViewModel(), IViewModel {

    var dataWeatherCity = MutableLiveData<DataWeatherCity>()

    override fun showWeather(dataWeatherCity: DataWeatherCity) {
        this.dataWeatherCity.value = dataWeatherCity
    }

    override fun showError() {
    }

    fun getWeather(cityMinsk: String) {
        repositoryWeather.getWeatherByCity(this, cityMinsk)
    }
    fun getWeatherAutoUpdate(cityMinsk: String){
        repositoryWeather.startAutoUpdate(this, cityMinsk)
    }
}