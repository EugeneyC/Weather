package com.skrebtsov.eugeney.weather.domain

import com.skrebtsov.eugeney.weather.di.modules.ParseResponse
import com.skrebtsov.eugeney.weather.di.modules.ParseResponse_ParseDateWeatherCityFactory
import com.skrebtsov.eugeney.weather.repository.RepositoryOneApi
import com.skrebtsov.eugeney.weather.view.viewmodel.IViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UseOneDataSource @Inject constructor(private val repositoryOneApi: RepositoryOneApi) {

    fun getWeatherByCity(viewModel: IViewModel, city: String) {
        val result =
            repositoryOneApi.getWeatherApiOpenweathermap().getWeatherInCity(cityName = city)
                .map {
                    ParseResponse_ParseDateWeatherCityFactory.parseDateWeatherCity(
                        ParseResponse(),
                        it
                    )
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewModel.showWeather(it)
                }, {
                    viewModel.showError()
                })
    }
}