package com.example.appsrp.api

import com.example.appsrp.api.RetrofitInstance.BASE_URL
import com.example.appsrp.api.ApiService
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance{
    private const val BASE_URL = "http://10.0.2.2:5000"
    val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }

}


