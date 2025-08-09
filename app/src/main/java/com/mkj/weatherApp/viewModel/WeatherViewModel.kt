package com.mkj.weatherApp.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkj.weatherApp.api.Constant
import com.mkj.weatherApp.api.RetrofitInstance
import com.mkj.weatherApp.dataModel.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherData = MutableLiveData<NetworkResponse<WeatherModel>>()
    val weatherData: MutableLiveData<NetworkResponse<WeatherModel>> = _weatherData

    fun getData(city: String) {
        Log.i("city", city)
        _weatherData.value = NetworkResponse.Loading
        viewModelScope.launch {
            try {
                val response = weatherApi.getWeather(Constant.APIKEY, city)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _weatherData.value = NetworkResponse.Success<WeatherModel>(it)
                    }
                } else {
                    _weatherData.value = NetworkResponse.Error("Failed To Load Data")
                }
            } catch (e: Exception) {
                _weatherData.value = NetworkResponse.Error("Failed To Load Data")
                Log.e("Error", e.message.toString())
            }
        }
    }
}

sealed class NetworkResponse<out T> {
    data class Success<T>(val data: T) : NetworkResponse<T>()
    data class Error(val message: String) : NetworkResponse<Nothing>()
    object Loading : NetworkResponse<Nothing>()
}