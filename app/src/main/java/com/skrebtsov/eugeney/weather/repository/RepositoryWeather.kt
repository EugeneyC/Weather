package com.skrebtsov.eugeney.weather.repository

import com.skrebtsov.eugeney.weather.getCoordCity
import com.skrebtsov.eugeney.weather.model.ParseResponse
import com.skrebtsov.eugeney.weather.model.ParseResponse_ParseDateWeatherCityFactory.parseDateWeatherCity
import com.skrebtsov.eugeney.weather.model.ParseResponse_ParseDateWeatherYandexCityFactory.parseDateWeatherYandexCity
import com.skrebtsov.eugeney.weather.model.UseOneDataSource
import com.skrebtsov.eugeney.weather.model.UseTwoDataSource
import com.skrebtsov.eugeney.weather.model.models.firstapi.DataWeatherCity
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
            getWeatherByCityAutoUpdate(city),
            getWeatherYandexByCityAutoUpdate(city)
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

    private fun getWeatherYandexByCityAutoUpdate(city: String): Observable<DataWeatherCity> {
        val coord = getCoordCity(city)
        return useTwoDataSource.getWeatherApiYandex()
            .weatherYandex(lon = coord?.lon.toString(), lat = coord?.lat.toString())
            .delay(5, TimeUnit.MINUTES)
            .map { parseDateWeatherYandexCity(ParseResponse(), it) }
    }

    private fun getWeatherByCityAutoUpdate(city: String): Observable<DataWeatherCity> {
        return useOneDataSource.getWeatherApiOpenweathermap().getWeatherInCity(cityName = city)
            .delay(3, TimeUnit.MINUTES)
            .map { parseDateWeatherCity(ParseResponse(), it) }
    }
}