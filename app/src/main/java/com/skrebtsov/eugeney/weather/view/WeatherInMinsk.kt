package com.skrebtsov.eugeney.weather.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import com.jakewharton.rxbinding2.view.RxView
import com.skrebtsov.eugeney.weather.R
import com.skrebtsov.eugeney.weather.databinding.ActivityWeatherInMinskBinding
import com.skrebtsov.eugeney.weather.di.App
import com.skrebtsov.eugeney.weather.viewmodel.WeatherByMinskViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class WeatherInMinsk : AppCompatActivity() {
    private val CITY_MINSK = "Minsk"
    private lateinit var binding: ActivityWeatherInMinskBinding
    private val disposableBag = CompositeDisposable()

    @Inject
    lateinit var viewModel: WeatherByMinskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather_in_minsk)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewmodel, viewModel)
        viewModel.getWeather(CITY_MINSK)

        disposableBag.add(RxView.clicks(binding.btnAutoUpdate)
            .subscribe {
                viewModel.getWeatherAutoUpdate(CITY_MINSK)
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposableBag.dispose()
        disposableBag.clear()
    }

}