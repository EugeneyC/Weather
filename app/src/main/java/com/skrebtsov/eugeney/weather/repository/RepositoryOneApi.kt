package com.skrebtsov.eugeney.weather.repository

import com.skrebtsov.eugeney.weather.di.modules.ParseResponse
import com.skrebtsov.eugeney.weather.di.modules.ParseResponse_ParseDateWeatherCityFactory.parseDateWeatherCity
import com.skrebtsov.eugeney.weather.model.WeatherApiOne
import com.skrebtsov.eugeney.weather.model.models.firstapi.DataWeatherCity
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RepositoryOneApi @Inject constructor(private val weatherApiOne: WeatherApiOne) {
    fun getWeatherApiOpenweathermap(): WeatherApiOne {
        return weatherApiOne
    }

    fun getWeatherByCityAutoUpdate(city: String): Observable<DataWeatherCity> {
        return getWeatherApiOpenweathermap().getWeatherInCity(cityName = city)
            .delay(3, TimeUnit.MINUTES)
            .map {
                parseDateWeatherCity(ParseResponse(), it)
            }
    }
}