package com.example.kshitijsih

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://api.stormglass.io/v2/"

    private val gson: Gson = GsonBuilder()
        .serializeNulls()  // Include fields with null values if needed
        .setLenient()      // Allow lenient parsing for relaxed JSON structure
        .create()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    inline fun <reified T> create(): T {
        return retrofit.create(T::class.java)
    }
}