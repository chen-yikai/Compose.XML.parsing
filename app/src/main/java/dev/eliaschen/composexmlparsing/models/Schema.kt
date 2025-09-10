package dev.eliaschen.composexmlparsing.models

data class City(
    val name: String,
    val nameEnglish: String,
    val fileName: String,
    val isCurrent: Boolean = false
)

data class Weather(
    val city: String,
    val latitude: String,
    val longitude: String,
    val hourlyForecast: List<Hour>
)

data class Hour(
    val time: String,
    val condition: String,
    val temperature: String
)