package com.skrebtsov.eugeney.weather.view

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import com.jakewharton.rxbinding2.widget.RxAdapterView
import com.skrebtsov.eugeney.weather.databinding.ActivityWeatherByCityBinding
import com.skrebtsov.eugeney.weather.getListCity
import com.skrebtsov.eugeney.weather.model.modelObject.DataWeatherCity
import com.skrebtsov.eugeney.weather.presenters.WeatherInCityActivityPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class WeatherByCity : MvpAppCompatActivity(), ContractWeatherByCity {

    private lateinit var binding: ActivityWeatherByCityBinding
    private val disposableBag = CompositeDisposable()

    private val presenter by moxyPresenter { WeatherInCityActivityPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherByCityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initAdapter()

        val disposable = RxAdapterView.itemSelections(binding.spinnerCity)
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe {
                presenter.getWeatherByCity(getListCity().get(it))
            }
        disposableBag.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposableBag.clear()
    }

    private fun initAdapter() {
        val arrayAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, R.layout.simple_spinner_item, getListCity())
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinnerCity.adapter = arrayAdapter
    }

    override fun showWeather(weather: DataWeatherCity) {
        binding.nameCity.text = weather.nameCity
        binding.tempInCity.text = "Temp: ${weather.tempInCity} °C"
        binding.weatherDescriptionInCity.text = "Weather: ${weather.weatherInCity}"
        binding.windInCity.text = "Wind: ${weather.wind} m/s"
    }

    override fun showError() {
        binding.tempInCity.text = "Error connecting server"
        binding.weatherDescriptionInCity.text = ""
        binding.windInCity.text = ""
    }
}