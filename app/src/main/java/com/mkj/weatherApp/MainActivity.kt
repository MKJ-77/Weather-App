package com.mkj.weatherApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.mkj.weatherApp.ui.theme.WeatherAppTheme
import com.mkj.weatherApp.viewModel.WeatherViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        setContent {
            WeatherAppTheme {
                WeatherScreen(viewModel)
            }
        }
    }
}
