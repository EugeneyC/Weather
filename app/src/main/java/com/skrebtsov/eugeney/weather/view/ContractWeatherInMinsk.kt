package com.skrebtsov.eugeney.weather.view

import com.skrebtsov.eugeney.weather.model.modelObject.WeatherModelResponce
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEndSingle

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface ContractWeatherInMinsk:MvpView {
    @AddToEndSingle
    fun showWeather(weather: WeatherModelResponce)
}