package com.skrebtsov.eugeney.weather.viewmodel

import com.skrebtsov.eugeney.weather.model.models.firstapi.DataWeatherCity

public interface IViewModel {
    fun showWeather(dataWeatherCity: DataWeatherCity)
    fun showError()
}