package com.skrebtsov.eugeney.weather.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skrebtsov.eugeney.weather.domain.UseCaseWeatherAutoUpdate
import com.skrebtsov.eugeney.weather.domain.UseOneDataSource
import com.skrebtsov.eugeney.weather.domain.UseTwoDataSource
import com.skrebtsov.eugeney.weather.model.models.firstapi.DataWeatherCity
import javax.inject.Inject

class WeatherByMinskViewModel @Inject constructor(
    private val useOneDataSource: UseOneDataSource,
    private val useTwoDataSource: UseTwoDataSource,
    private val useCaseWeatherAutoUpdate: UseCaseWeatherAutoUpdate
) : ViewModel(), IViewModel {

    var dataWeatherCity = MutableLiveData<DataWeatherCity>()

    override fun showWeather(dataWeatherCity: DataWeatherCity) {
        this.dataWeatherCity.value = dataWeatherCity
    }

    override fun showError() {
    }

    fun getWeather(cityMinsk: String) {
        useOneDataSource.getWeatherByCity(this, cityMinsk)
    }

    fun getWeatherAutoUpdate(cityMinsk: String) {
        useCaseWeatherAutoUpdate.startAutoUpdate(this, cityMinsk)
    }
}