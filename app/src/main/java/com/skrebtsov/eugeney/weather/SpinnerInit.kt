package com.skrebtsov.eugeney.weather

import com.skrebtsov.eugeney.weather.model.models.firstapi.Coord

val dataCity = mapOf(
    "Minsk" to Coord(53.902284, 27.561831),
    "Grodno" to Coord(53.677839, 23.829528),
    "Gomel" to Coord(52.424162, 31.014214),
    "Vitebsk" to Coord(55.184192, 30.202874),
    "Brest" to Coord(52.094237, 23.684564),
    "Mogilev" to Coord(53.894539, 30.330594)
)

fun getListCity(): List<String> {
    val list: ArrayList<String> = ArrayList()
    for (nameCity in dataCity) {
        list.add(nameCity.key)
    }
    return list
}

fun getListCoordCity(city: String): Coord? {
    return dataCity[city]
}

