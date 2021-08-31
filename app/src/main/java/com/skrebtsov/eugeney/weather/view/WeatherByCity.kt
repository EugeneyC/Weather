package com.skrebtsov.eugeney.weather.view

import android.os.Bundle
import android.widget.ArrayAdapter
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
        binding = ActivityWeatherByCityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initAdapter()

        val disposable = RxAdapterView.itemSelections(binding.spinnerCity)
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe {
                presenter.getWeatherByCity(getListCity()[it])
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
        binding.tempInCity.text = "${weather.tempInCity} °C"
        val resIconWeather = resources.getIdentifier(("_" + weather.icon), "drawable", packageName)
        binding.weatherDescriptionInCity.setImageResource(resIconWeather)
        binding.windInCity.text = "${weather.wind} m/s"
        binding.imageWind.setImageResource(R.drawable.wind_white)
    }

    override fun showError() {
        binding.nameCity.text = "Error connecting server"
        binding.tempInCity.text = ""
        binding.windInCity.text = ""
    }
}