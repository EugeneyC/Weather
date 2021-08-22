package com.skrebtsov.eugeney.weather.view

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import com.jakewharton.rxbinding2.widget.RxAdapterView
import com.skrebtsov.eugeney.weather.databinding.ActivityWeatherByCityBinding
import com.skrebtsov.eugeney.weather.getListCity
import com.skrebtsov.eugeney.weather.model.modelObject.WeatherModelResponce
import com.skrebtsov.eugeney.weather.presenters.WeatherInCityActivityPresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class WeatherByCity : MvpAppCompatActivity(), ContractWeatherByCity {

    private lateinit var binding: ActivityWeatherByCityBinding

    private val presenter by moxyPresenter { WeatherInCityActivityPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherByCityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initAdapter()

        RxAdapterView.itemSelections(binding.spinnerCity)
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binding.nameCity.text = getListCity().get(it)
                presenter.getWeatherByCity(getListCity().get(it))
            }

    }

    private fun initAdapter() {
        val arrayAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, R.layout.simple_spinner_item, getListCity())
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinnerCity.adapter = arrayAdapter
    }

    override fun showWeather(weather: WeatherModelResponce) {
        val tempInCity = weather.getMain()?.getTemp().toString()
        val weatherInCity = weather.getWeather()?.get(0)?.getDescription().toString()
        val wind = weather.getWind()?.getSpeed().toString()

        binding.tempInCity.text = "Temp: $tempInCity °C"
        binding.weatherDescriptionInCity.text = "Weather: $weatherInCity"
        binding.windInCity.text = "Wind: $wind m/s"
    }

    override fun showError() {
        binding.tempInCity.text = "Error connecting server"
    }
}