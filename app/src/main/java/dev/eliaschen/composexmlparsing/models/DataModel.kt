package dev.eliaschen.composexmlparsing.models

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.AndroidViewModel
import org.w3c.dom.Element
import javax.xml.parsers.DocumentBuilderFactory

class DataModel(private val context: Application) : AndroidViewModel(context) {
    val cityList = mutableStateListOf<City>()
    val weathers = mutableStateMapOf<String, Weather>()

    init {
        loadCityList()
        loadWeather()
    }

    private fun loadWeather() {
        cityList.forEach { city ->
            val builder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
            val file = context.assets.open(city.fileName)
            val element = builder.parse(file).documentElement
            val hours = element.getElementsByTagName("hour")
            Log.i("xml",city.fileName)
            val weather = Weather(
                city = element.getElementsByTagName("city").item(0).textContent,
                latitude = element.getElementsByTagName("latitude").item(0).textContent,
                longitude = element.getElementsByTagName("longitude").item(0).textContent,
                hourlyForecast = List(hours.length) { index ->
                    val currentHour = hours.item(index) as Element
                    Hour(
                        time = currentHour.getElementsByTagName("time").item(0).textContent,
                        condition = currentHour.getElementsByTagName("weather_condition")
                            .item(0).textContent,
                        temperature = currentHour.getElementsByTagName("temperature")
                            .item(0).textContent
                    )
                }
            )
            weathers[city.fileName] = weather
        }
    }

    private fun loadCityList() {
        val builder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
        val file = context.assets.open("city_list.xml")
        val elements = builder.parse(file).documentElement
        val city = elements.getElementsByTagName("city")
        val list = List(city.length) { index ->
            val currentCity = city.item(index) as Element
            City(
                name = currentCity.getElementsByTagName("name").item(0).textContent,
                nameEnglish = currentCity.getElementsByTagName("name_en").item(0).textContent,
                fileName = currentCity.getElementsByTagName("file_name").item(0).textContent,
                isCurrent = currentCity.getAttribute("type") == "current"
            )
        }
        cityList.clear()
        cityList.addAll(list)
    }
}