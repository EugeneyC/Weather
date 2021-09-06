package com.skrebtsov.eugeney.weather.view

import android.os.Bundle
import com.jakewharton.rxbinding2.view.RxView
import com.skrebtsov.eugeney.weather.R
import com.skrebtsov.eugeney.weather.databinding.ActivityWeatherInMinskBinding
import com.skrebtsov.eugeney.weather.model.models.firstapi.DataWeatherCity
import com.skrebtsov.eugeney.weather.presenters.WeatherInCityActivityPresenter
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter


class WeatherInMinsk : MvpAppCompatActivity(), ContractWeatherByCity {
    private val CITY_MINSK = "Minsk"
    private lateinit var binding: ActivityWeatherInMinskBinding
    private val disposableBag = CompositeDisposable()

    private val presenter by moxyPresenter { WeatherInCityActivityPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherInMinskBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        presenter.getWeatherByCity(CITY_MINSK)

        disposableBag.add(RxView.clicks(binding.btnAutoUpdate)
            .subscribe {
                presenter.startAutoUpdate(CITY_MINSK)
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposableBag.dispose()
        disposableBag.clear()
    }

    override fun showWeather(weather: DataWeatherCity) {
        binding.textView.text = weather.nameCity
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