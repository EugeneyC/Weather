package com.skrebtsov.eugeney.weather.domain

import com.skrebtsov.eugeney.weather.di.modules.ParseResponse
import com.skrebtsov.eugeney.weather.di.modules.ParseResponse_ParseDateWeatherYandexCityFactory.parseDateWeatherYandexCity
import com.skrebtsov.eugeney.weather.model.data.DataCity
import com.skrebtsov.eugeney.weather.model.data.DataCity_GetCoordCityFactory.getCoordCity
import com.skrebtsov.eugeney.weather.repository.RepositoryTwoApi
import com.skrebtsov.eugeney.weather.view.viewmodel.IViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UseTwoDataSource @Inject constructor(private val repositoryTwoApi: RepositoryTwoApi) {

    fun getWeatherByCity(viewModel: IViewModel, city: String) {
        val coord = getCoordCity(DataCity(), city)
        val result =
            repositoryTwoApi.getWeatherApiYandex()
                .weatherYandex(lon = coord?.lon.toString(), lat = coord?.lat.toString())
                .map {
                    parseDateWeatherYandexCity(ParseResponse(), it)
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