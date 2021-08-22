package com.skrebtsov.eugeney.weather.view

import android.os.Bundle
import com.skrebtsov.eugeney.weather.R
import com.skrebtsov.eugeney.weather.databinding.ActivityWeatherInMinskBinding
import com.skrebtsov.eugeney.weather.model.modelObject.WeatherModelResponce
import com.skrebtsov.eugeney.weather.presenters.WeatherInMinskActivityPresenter
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter


class WeatherInMinsk : MvpAppCompatActivity(R.layout.activity_weather_in_minsk),
    ContractWeatherInMinsk {

    private lateinit var binding: ActivityWeatherInMinskBinding

    private val presenter by moxyPresenter { WeatherInMinskActivityPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherInMinskBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        presenter.getWeatherInMinsk()
    }

    override fun showWeather(weather: WeatherModelResponce) {
        val tempInCity = weather.getMain()?.getTemp().toString()
        val weatherInCity = weather.getWeather()?.get(0)?.getDescription().toString()
        val wind = weather.getWind()?.getSpeed().toString()
        binding.tempInMinsk.text = "Temp: $tempInCity C"
        binding.weatherDescriptionInMinsk.text = "Weather: $weatherInCity"
        binding.windInMinsk.text = "Wind: $wind m/s"
    }
}