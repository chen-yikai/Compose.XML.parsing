package dev.eliaschen.composexmlparsing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.ViewModelProvider
import dev.eliaschen.composexmlparsing.models.DataModel
import dev.eliaschen.composexmlparsing.models.NavController
import dev.eliaschen.composexmlparsing.models.Screen
import dev.eliaschen.composexmlparsing.ui.theme.ComposeXMLparsingTheme

val LocalDataModel = compositionLocalOf<DataModel> { error("LocalDataModel") }
val LocalNavController = compositionLocalOf<NavController> { error("NavController") }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeXMLparsingTheme {
                val dataModel = ViewModelProvider(this)[DataModel::class.java]
                val navController = ViewModelProvider(this)[NavController::class.java]

                CompositionLocalProvider(
                    LocalDataModel provides dataModel,
                    LocalNavController provides navController
                ) {
                    when(navController.currentScreen){
                        Screen.Home -> HomeScreen()
                        Screen.Weather -> WeatherScreen()
                    }
                }
            }
        }
    }
}