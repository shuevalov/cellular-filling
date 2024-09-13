package ru.shuevalov.cellular_filling

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ru.shuevalov.cellular_filling.ui.home.HomeScreen
import ru.shuevalov.cellular_filling.ui.theme.CellularFillingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CellularFillingTheme {
                HomeScreen()
            }
        }
    }
}