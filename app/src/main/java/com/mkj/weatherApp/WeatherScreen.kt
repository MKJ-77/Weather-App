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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mkj.weatherApp.dataModel.WeatherModel
import com.mkj.weatherApp.viewModel.NetworkResponse
import com.mkj.weatherApp.viewModel.WeatherViewModel


@Composable
fun WeatherScreen(weatherViewModel: WeatherViewModel) {
    var city by remember { mutableStateOf("") }
    val weatherData = weatherViewModel.weatherData.observeAsState()
    val verticalScroll = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(verticalScroll)
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
    ) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.surface,
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .weight(1f),
                    value = city,
                    shape = RoundedCornerShape(12.dp),
                    onValueChange = {
                        city = it
                    },
                    label = {
                        Text(
                            text = "Search For any City",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            color = Color.Gray,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                )
                IconButton(
                    modifier = Modifier
                        .size(56.dp)
                        .padding(8.dp),
                    onClick = { weatherViewModel.getData(city) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        modifier = Modifier
                            .size(35.dp)
                    )
                }
            }
        }
        Spacer(Modifier.height(20.dp))
        when (val result = weatherData.value) {
            is NetworkResponse.Error -> {
                Text(
                    result.message,
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 30.sp
                )
            }

            is NetworkResponse.Loading -> {
                CircularProgressIndicator()
            }

            is NetworkResponse.Success -> {
                WeatherDisplayDataCard(result.data)
            }
            null -> {
            }
        }
    }
}



