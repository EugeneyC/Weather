package com.skrebtsov.eugeney.weather.model.models.secondapi


data class Parts(
    var night: Night,
    var evening: Evening,
    var dayShort: DayShort,
    var nightShort: NightShort,
    var morning: Morning,
    var day: Day
)