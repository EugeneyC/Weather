package com.skrebtsov.eugeney.weather.di.modules

import com.skrebtsov.eugeney.weather.model.models.firstapi.DataWeatherCity
import com.skrebtsov.eugeney.weather.model.models.firstapi.WeatherOpenWeathermapResponse
import com.skrebtsov.eugeney.weather.model.models.secondapi.WeatherYandexResponse
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ParseResponse {
    @Singleton
    @Provides
    fun parseDateWeatherYandexCity(weatherYandexResponse: WeatherYandexResponse): DataWeatherCity {
        return DataWeatherCity(
            tempInCity = weatherYandexResponse.fact.temp.toString(),
            weatherInCity = weatherYandexResponse.fact.condition,
            wind = weatherYandexResponse.fact.windSpeed.toString(),
            icon = weatherYandexResponse.fact.icon
        )
    }
    @Singleton
    @Provides
    fun parseDateWeatherCity(weatherOpenWeatherMapResponse: WeatherOpenWeathermapResponse): DataWeatherCity {
        return DataWeatherCity(
            nameCity = weatherOpenWeatherMapResponse.name.toString(),
            tempInCity = weatherOpenWeatherMapResponse.main?.temp.toString(),
            weatherInCity = weatherOpenWeatherMapResponse.weather?.get(0)?.description.toString(),
            wind = weatherOpenWeatherMapResponse.wind?.speed.toString(),
            icon = weatherOpenWeatherMapResponse.weather?.get(0)?.icon.toString()
        )
    }
}