package com.mkj.weatherApp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkj.weatherApp.api.Constant
import com.mkj.weatherApp.api.RetrofitInstance
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val weatherApi = RetrofitInstance.weatherApi
    fun getData(city: String) {
        Log.i("city", city)
        viewModelScope.launch {
            try {
                val response = weatherApi.getWeather(Constant.APIKEY, city)
                if (response.isSuccessful) {
                    val weatherModel = response.body()
                    Log.i("weatherModel", weatherModel.toString())
                }else{
                    Log.e("Error", response.message())
                }
            }catch (e :Exception){
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