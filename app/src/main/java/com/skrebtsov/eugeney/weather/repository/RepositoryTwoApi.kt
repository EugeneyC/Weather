package com.skrebtsov.eugeney.weather.repository

import com.skrebtsov.eugeney.weather.di.modules.ParseResponse
import com.skrebtsov.eugeney.weather.model.WeatherApiTwo
import com.skrebtsov.eugeney.weather.model.data.DataCity
import com.skrebtsov.eugeney.weather.model.data.DataCity_GetCoordCityFactory.getCoordCity
import com.skrebtsov.eugeney.weather.model.models.firstapi.DataWeatherCity
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RepositoryTwoApi @Inject constructor(
    private val weatherApiTwo: WeatherApiTwo,
    private val parseResponse: ParseResponse,
) {
    fun getWeatherApiYandex(): WeatherApiTwo {
        return weatherApiTwo
    }

    fun getWeatherYandexByCityAutoUpdate(city: String): Observable<DataWeatherCity> {
        val coord = getCoordCity(DataCity(), city)
        return getWeatherApiYandex()
            .weatherYandex(lon = coord?.lon.toString(), lat = coord?.lat.toString())
            .delay(5, TimeUnit.MINUTES)
            .map {
                parseResponse.parseDateWeatherYandexCity(it)
            }
    }
}
