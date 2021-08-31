package com.skrebtsov.eugeney.weather.view

import com.skrebtsov.eugeney.weather.model.models.firstapi.DataWeatherCity
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface ContractWeatherByCity:MvpView {
    fun showWeather(weather: DataWeatherCity)
    fun showError()
}