package com.skrebtsov.eugeney.weather.viewmodel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skrebtsov.eugeney.weather.model.models.firstapi.DataWeatherCity
import com.skrebtsov.eugeney.weather.repository.RepositoryWeather
import javax.inject.Inject

class WeatherByCityViewModel @Inject constructor(private val repositoryWeather: RepositoryWeather) :
    ViewModel(), IViewModel {

    var dataWeatherCity = MutableLiveData<DataWeatherCity>()

    fun getWeather(city: String) {
        repositoryWeather.getWeatherByCity(this, city)
    }

    fun  getWeatherAutoUpdate(city: String){
        repositoryWeather.startAutoUpdate(this, city)
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