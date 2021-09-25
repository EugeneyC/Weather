package com.skrebtsov.eugeney.weather.model

import javax.inject.Inject

class UseOneDataSource @Inject constructor(private val weatherApiOne: WeatherApiOne){
        fun getWeatherApiOpenweathermap():WeatherApiOne{
            return weatherApiOne
        }
}