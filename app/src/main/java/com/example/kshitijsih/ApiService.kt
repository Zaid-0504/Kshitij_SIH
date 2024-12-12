package com.example.kshitijsih

import com.google.android.gms.common.api.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface ApiService {


    @GET("weather/point")
    fun fetch_data(
        @Header("Authorization") token: String,
        @Query("lat") lat:Double,
        @Query("lng") lng:Double,
        @Query("params") params:String,
        @Query("start") start:String,
        @Query("end") end:String
    ): Call<BeachDataResponse>
}