package com.skrebtsov.eugeney.weather.view

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.jakewharton.rxbinding2.widget.RxAdapterView
import com.skrebtsov.eugeney.weather.R
import com.skrebtsov.eugeney.weather.databinding.ActivityWeatherByCityBinding
import com.skrebtsov.eugeney.weather.getListCity
import com.skrebtsov.eugeney.weather.model.models.firstapi.DataWeatherCity
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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather_by_city)

        initAdapter()

        val disposable = RxAdapterView.itemSelections(binding.spinnerCity)
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe {
                presenter.getWeatherByCity(getListCity()[it])
                presenter.startAutoUpdate(getListCity()[it])
            }
        disposableBag.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposableBag.clear()
    }

    private fun initAdapter() {
        val arrayAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, R.layout.spinner_style, getListCity())
        binding.spinnerCity.adapter = arrayAdapter
    }

    override fun showWeather(weather: DataWeatherCity) {
        binding.dataWeatherCity = weather
    }

    override fun showError() {
        binding.nameCity.text = "Error connecting server"
    }
}