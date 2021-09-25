package com.skrebtsov.eugeney.weather.di

import com.skrebtsov.eugeney.weather.model.modules.NetworkModuleOpenweathermap
import com.skrebtsov.eugeney.weather.model.modules.NetworkModuleYandex
import com.skrebtsov.eugeney.weather.view.WeatherByCity
import com.skrebtsov.eugeney.weather.view.WeatherInMinsk
import dagger.Component

@Component(modules = [NetworkModuleYandex::class, NetworkModuleOpenweathermap::class])
public abstract interface AppComponent {
    fun inject(appCompatActivity: WeatherByCity)
    fun inject(appCompatActivity: WeatherInMinsk)
}