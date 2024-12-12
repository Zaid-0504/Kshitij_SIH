package com.example.kshitijsih

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BeachViewModel: ViewModel() {

    private val _beachData = MutableLiveData<BeachDataResponse?>()
    val beachData: MutableLiveData<BeachDataResponse?> get() = _beachData

    private val apiService= RetrofitInstance.create<ApiService>()
    private val repository= BeachRepository(apiService)

    fun loadBeachData(beachData: BeachData,token:String):LiveData<BeachDataResponse> {
        val result = repository.fetch_data(beachData,token)
        return result
//        viewModelScope.launch {
//            val result = repository.fetch_data(beachData,token)
//            Log.d("Retrofit", "loadBeachData: "+ (result.value?.hours?.get(0)?.time ?: "no data"))
//            _beachData.postValue(result.value)
//        }
    }
}