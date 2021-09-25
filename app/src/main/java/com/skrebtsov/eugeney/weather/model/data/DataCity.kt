package com.skrebtsov.eugeney.weather.model.data

import com.skrebtsov.eugeney.weather.model.models.firstapi.Coord
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataCity {

    val dataCity = listOf(
        City("Minsk", Coord(53.902284, 27.561831)),
        City("Grodno", Coord(53.677839, 23.829528)),
        City("Gomel", Coord(52.424162, 31.014214)),
        City("Vitebsk", Coord(55.184192, 30.202874)),
        City("Brest", Coord(52.094237, 23.684564)),
        City("Mogilev", Coord(53.894539, 30.330594))
    )

    @Singleton
    @Provides
    fun getListCity(): List<String> {
        return dataCity.map(City::name).sorted()
    }

    @Singleton
    @Provides
    fun getCoordCity(cityName: String): Coord? {
        val city = dataCity.find { it.name == cityName }
        return city?.coord
    }
}