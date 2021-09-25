package com.skrebtsov.eugeney.weather.view

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import com.jakewharton.rxbinding2.widget.RxAdapterView
import com.skrebtsov.eugeney.weather.R
import com.skrebtsov.eugeney.weather.databinding.ActivityWeatherByCityBinding
import com.skrebtsov.eugeney.weather.di.App
import com.skrebtsov.eugeney.weather.model.data.DataCity
import com.skrebtsov.eugeney.weather.model.data.DataCity_GetListCityFactory.getListCity
import com.skrebtsov.eugeney.weather.view.viewmodel.WeatherByCityViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class WeatherByCity : AppCompatActivity() {
    lateinit var binding: ActivityWeatherByCityBinding

    @Inject lateinit var viewModel: WeatherByCityViewModel

    private val disposableBag = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather_by_city)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewmodel, viewModel)

        initAdapter()

        val disposable = RxAdapterView.itemSelections(binding.spinnerCity)
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe {
                viewModel.getWeather(getListCity(DataCity())[it])
                viewModel.getWeatherAutoUpdate(getListCity(DataCity())[it])
            }
        disposableBag.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposableBag.clear()
    }

    private fun initAdapter() {
        val arrayAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, R.layout.spinner_style, getListCity(DataCity()))
            binding.spinnerCity.adapter = arrayAdapter
    }
}