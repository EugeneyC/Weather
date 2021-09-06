package com.skrebtsov.eugeney.weather.view

import android.os.Bundle
import android.widget.ImageView
import com.jakewharton.rxbinding2.view.RxView
import com.skrebtsov.eugeney.weather.R
import com.skrebtsov.eugeney.weather.databinding.ActivityWeatherInMinskBinding
import com.skrebtsov.eugeney.weather.model.models.firstapi.DataWeatherCity
import com.skrebtsov.eugeney.weather.presenters.WeatherInCityActivityPresenter
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil


class WeatherInMinsk : MvpAppCompatActivity(), ContractWeatherByCity {
    private val CITY_MINSK = "Minsk"
    private lateinit var binding: ActivityWeatherInMinskBinding
    private val disposableBag = CompositeDisposable()

    private val presenter by moxyPresenter { WeatherInCityActivityPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather_in_minsk)

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
        binding.dataWeatherCity = weather
    }

    override fun showError() {
        binding.tempInMinsk.text = "Error connecting server"
        binding.windInMinsk.text = ""
    }

    @BindingAdapter("android:imgWeather")
    fun setImageViewResource(imageView: ImageView, resource: Int) {
        imageView.setImageResource(resource)
    }
}