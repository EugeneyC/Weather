package com.skrebtsov.eugeney.weather.view.viewmodel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skrebtsov.eugeney.weather.domain.UseCaseWeatherAutoUpdate
import com.skrebtsov.eugeney.weather.domain.UseOneDataSource
import com.skrebtsov.eugeney.weather.domain.UseTwoDataSource
import com.skrebtsov.eugeney.weather.model.models.firstapi.DataWeatherCity
import javax.inject.Inject

class WeatherByCityViewModel @Inject constructor(
    private val useOneDataSource: UseOneDataSource,
    private val useTwoDataSource: UseTwoDataSource,
    private val useCaseWeatherAutoUpdate: UseCaseWeatherAutoUpdate
) :
    ViewModel(), IViewModel {

    var dataWeatherCity = MutableLiveData<DataWeatherCity>()

    fun getWeather(city: String) {
        useOneDataSource.getWeatherByCity(this, city)
    }

    fun getWeatherAutoUpdate(city: String) {
        useCaseWeatherAutoUpdate.startAutoUpdate(this, city)
    }

    override fun showWeather(dataWeatherCity: DataWeatherCity) {
        this.dataWeatherCity.value = dataWeatherCity
    }

    override fun showError() {
//            dataWeatherCity.value?.nameCity = "Error connecting server"
    }

    @BindingAdapter("android:imgWeather")
    fun setImageViewResource(imageView: ImageView, resource: Int) {
        imageView.setImageResource(resource)
    }
}