package com.example.kshitijsih

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeachRepository(val apiService: ApiService) {

     fun fetch_data(beachData: BeachData,token:String):LiveData<BeachDataResponse> {

        val data=MutableLiveData<BeachDataResponse>()

        val call= apiService.fetch_data(token,
            beachData.lat,
            beachData.lng,
            beachData.params,
            beachData.start,
            beachData.end)

        call.enqueue(object : Callback<BeachDataResponse?> {
            override fun onResponse(
                call: Call<BeachDataResponse?>,
                response: Response<BeachDataResponse?>
            ) {
                if (response.isSuccessful()) {
                    Log.d("Retrofit", "onResponse:"+ (response.body()?.hours?.get(0)?.time ?: "empty"))
                    data.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<BeachDataResponse?>, throwable: Throwable) {
                Log.d("Retrofit", "onResponse:$throwable")
            }
        })

        return data
    }
}