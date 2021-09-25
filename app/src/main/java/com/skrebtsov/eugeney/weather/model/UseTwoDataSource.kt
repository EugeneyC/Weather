package com.skrebtsov.eugeney.weather.model

import javax.inject.Inject

class UseTwoDataSource @Inject constructor(private val weatherApiTwo: WeatherApiTwo) {
    fun getWeatherApiYandex():WeatherApiTwo{
        return weatherApiTwo
    }
}