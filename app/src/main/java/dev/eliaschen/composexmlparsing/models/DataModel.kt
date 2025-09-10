package dev.eliaschen.composexmlparsing.models

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import org.w3c.dom.Element
import javax.xml.parsers.DocumentBuilderFactory

class DataModel(private val context: Application) : AndroidViewModel(context) {
    val cityList = mutableStateListOf<City>()

    init {
        loadCityList()
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
                nameEnglish = elements.getElementsByTagName("name_en").item(0).textContent,
                fileName = elements.getElementsByTagName("file_name").item(0).textContent,
                isCurrent = currentCity.getAttribute("type") == "current"
            )
        }
        cityList.clear()
        cityList.addAll(list)
    }
}