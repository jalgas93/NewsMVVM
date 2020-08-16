package com.androiddevs.mvvmnewsapp.api

import com.androiddevs.mvvmnewsapp.util.Constanta.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    private  val retrofit by lazy {

        val loggin = HttpLoggingInterceptor()
        loggin.setLevel(HttpLoggingInterceptor.Level.BODY)


        val client = OkHttpClient.Builder()
            .addInterceptor(loggin)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create())
            .build()

    }


    val api by lazy {
        retrofit.create(NewsApi::class.java)
    }
}