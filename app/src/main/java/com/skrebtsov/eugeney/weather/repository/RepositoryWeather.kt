package com.skrebtsov.eugeney.weather.repository

import com.skrebtsov.eugeney.weather.model.ParseResponse
import com.skrebtsov.eugeney.weather.model.ParseResponse_ParseDateWeatherCityFactory.parseDateWeatherCity
import com.skrebtsov.eugeney.weather.model.UseOneDataSource
import com.skrebtsov.eugeney.weather.model.UseTwoDataSource
import com.skrebtsov.eugeney.weather.view.viewmodel.IViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RepositoryWeather @Inject constructor(
    private val useOneDataSource: UseOneDataSource,
    private val useTwoDataSource: UseTwoDataSource
) {

    fun startAutoUpdate(viewModel: IViewModel, city: String) {
        val result = Observable.merge(
            useOneDataSource.getWeatherByCityAutoUpdate(city),
            useTwoDataSource.getWeatherYandexByCityAutoUpdate(city)
        )
            .repeat()
            .timeInterval(TimeUnit.MINUTES)
            .flatMap {
                if (it.time() <= 1) {
                    Observable.just(it.value()).delay(1, TimeUnit.MINUTES)
                } else {
                    Observable.just(it.value())
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewModel.showWeather(it)
            },
                {
                    viewModel.showError()
                })
    }

    fun getWeatherByCity(viewModel: IViewModel, city: String) {
        val result =
            useOneDataSource.getWeatherApiOpenweathermap().getWeatherInCity(cityName = city)
                .map { parseDateWeatherCity(ParseResponse(), it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewModel.showWeather(it)
                }, {
                    viewModel.showError()
                })
    }
}