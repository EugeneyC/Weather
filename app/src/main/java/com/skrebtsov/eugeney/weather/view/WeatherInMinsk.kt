package com.skrebtsov.eugeney.weather.view

import android.os.Bundle
import com.skrebtsov.eugeney.weather.databinding.ActivityWeatherInMinskBinding
import com.skrebtsov.eugeney.weather.model.modelObject.DataWeatherCity
import com.skrebtsov.eugeney.weather.presenters.WeatherInCityActivityPresenter
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter


class WeatherInMinsk : MvpAppCompatActivity(), ContractWeatherByCity {

    private var CITY = "Minsk"
    private lateinit var binding: ActivityWeatherInMinskBinding

    private val presenter by moxyPresenter { WeatherInCityActivityPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherInMinskBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        presenter.getWeatherByCity(CITY)
    }

    override fun showWeather(weather: DataWeatherCity) {
        binding.tempInMinsk.text = "Temp: ${weather.tempInCity} °C"
        binding.weatherDescriptionInMinsk.text = "Weather: ${weather.weatherInCity}"
        binding.windInMinsk.text = "Wind: ${weather.wind} m/s"
    }

    override fun showError() {
        binding.tempInMinsk.text = "Error connecting server"
        binding.weatherDescriptionInMinsk.text = ""
        binding.windInMinsk.text = ""
    }
}