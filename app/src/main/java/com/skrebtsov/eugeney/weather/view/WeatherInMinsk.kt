package com.skrebtsov.eugeney.weather.view

import android.os.Bundle
import com.skrebtsov.eugeney.weather.R
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
        binding.tempInMinsk.text = "${weather.tempInCity} °C"
        val resIconWeather = resources.getIdentifier(("_" + weather.icon), "drawable", packageName)
        binding.weatherDescriptionInMinsk.setImageResource(resIconWeather)
        binding.windInMinsk.text = "${weather.wind} m/s"
        binding.imageWind.setImageResource(R.drawable.wind_white)
    }

    override fun showError() {
        binding.tempInMinsk.text = "Error connecting server"
        binding.windInMinsk.text = ""
    }
}