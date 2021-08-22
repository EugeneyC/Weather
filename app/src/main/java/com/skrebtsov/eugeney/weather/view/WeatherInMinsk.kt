package com.skrebtsov.eugeney.weather.view

import android.os.Bundle
import com.skrebtsov.eugeney.weather.databinding.ActivityWeatherInMinskBinding
import com.skrebtsov.eugeney.weather.model.modelObject.WeatherModelResponce
import com.skrebtsov.eugeney.weather.presenters.WeatherInCityActivityPresenter
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter


class WeatherInMinsk : MvpAppCompatActivity(),
    ContractWeatherByCity {
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

    override fun showWeather(weather: WeatherModelResponce) {
        val tempInCity = weather.getMain()?.getTemp().toString()
        val weatherInCity = weather.getWeather()?.get(0)?.getDescription().toString()
        val wind = weather.getWind()?.getSpeed().toString()

        binding.tempInMinsk.text = "Temp: $tempInCity °C"
        binding.weatherDescriptionInMinsk.text = "Weather: $weatherInCity"
        binding.windInMinsk.text = "Wind: $wind m/s"
    }

    override fun showError() {
        binding.tempInMinsk.text = "Error connecting server"
    }
}