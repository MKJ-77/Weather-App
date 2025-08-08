package com.mkj.weatherApp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mkj.weatherApp.viewModel.WeatherViewModel


@Composable
fun WeatherScreen(weatherViewModel: WeatherViewModel) {
    var city by remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
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
                    onClick = {weatherViewModel.getData(city)}
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
    }
}
