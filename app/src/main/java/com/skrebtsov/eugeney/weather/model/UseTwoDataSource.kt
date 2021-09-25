package com.skrebtsov.eugeney.weather.model

import com.skrebtsov.eugeney.weather.model.ParseResponse_ParseDateWeatherYandexCityFactory.parseDateWeatherYandexCity
import com.skrebtsov.eugeney.weather.model.data.DataCity
import com.skrebtsov.eugeney.weather.model.data.DataCity_GetCoordCityFactory.getCoordCity
import com.skrebtsov.eugeney.weather.model.models.firstapi.DataWeatherCity
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UseTwoDataSource @Inject constructor(private val weatherApiTwo: WeatherApiTwo) {
    fun getWeatherApiYandex(): WeatherApiTwo {
        return weatherApiTwo
    }

    fun getWeatherYandexByCityAutoUpdate(city: String): Observable<DataWeatherCity> {
        val coord = getCoordCity(DataCity(), city)
        return getWeatherApiYandex()
            .weatherYandex(lon = coord?.lon.toString(), lat = coord?.lat.toString())
            .delay(5, TimeUnit.MINUTES)
            .map {
                parseDateWeatherYandexCity(ParseResponse(), it)
            }
    }

}