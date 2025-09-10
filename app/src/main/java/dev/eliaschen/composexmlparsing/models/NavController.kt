package dev.eliaschen.composexmlparsing.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class NavController : ViewModel() {
    private val initScreen = Screen.Home
    var currentScreen by mutableStateOf(initScreen)

    fun navTo(screen: Screen) {
        currentScreen = screen
    }
}

enum class Screen {
    Home, Weather
}