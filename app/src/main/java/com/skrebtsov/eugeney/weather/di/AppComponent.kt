package com.skrebtsov.eugeney.weather.di

import com.skrebtsov.eugeney.weather.di.modules.NetworkModuleOpenweathermap
import com.skrebtsov.eugeney.weather.di.modules.NetworkModuleYandex
import com.skrebtsov.eugeney.weather.model.ParseResponse
import com.skrebtsov.eugeney.weather.model.data.DataCity
import com.skrebtsov.eugeney.weather.view.WeatherByCity
import com.skrebtsov.eugeney.weather.view.WeatherInMinsk
import dagger.Component

@Component(
    modules = [NetworkModuleYandex::class,
        NetworkModuleOpenweathermap::class,
        ParseResponse::class, DataCity::class]
)
interface AppComponent {
    fun inject(appCompatActivity: WeatherByCity)
    fun inject(appCompatActivity: WeatherInMinsk)
}