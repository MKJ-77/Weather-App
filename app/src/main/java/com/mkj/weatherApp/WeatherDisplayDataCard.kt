package com.mkj.weatherApp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mkj.weatherApp.dataModel.WeatherModel

@Composable
fun WeatherDisplayDataCard(weatherData: WeatherModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Place,
                contentDescription = "Location Icon",
                modifier = Modifier.size(60.dp)
            )
            Text(
                "${weatherData.location.name}, ${weatherData.location.region}",
                fontSize = 30.sp
            )
        }
        Text(
            weatherData.location.country,
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.height(10.dp))
        Text(
            "${weatherData.current.temp_c}°C",
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = "https:${weatherData.current.condition.icon}",
                modifier = Modifier.size(120.dp),
                contentDescription = "Weather Icon"
            )
            Text(
                weatherData.current.condition.text,
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(Modifier.height(5.dp))
        Text(
            "Last Updated: ${weatherData.current.last_updated}",
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.height(20.dp))
        Surface(
            shape = RoundedCornerShape(30f),
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                DataRow("Humidity", "${weatherData.current.humidity}%")
                DataRow("Wind Speed", "${weatherData.current.wind_kph}km/h")
                DataRow("Feels Like", "${weatherData.current.feelslike_c}°C")
                DataRow("Cloud", "${weatherData.current.cloud}%")
            }
        }
    }
}

@Composable
fun DataRow(heading: String, value: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            heading,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 20.sp,
            fontWeight = FontWeight.W700
        )
        Text(
            value,
            fontSize = 18.sp,
            fontWeight = FontWeight.W500,
            textAlign = TextAlign.Center
        )
    }
}