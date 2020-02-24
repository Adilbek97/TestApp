package com.example.testapp.network

import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    //create Logger
    private val logger= HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    // create okHttp Client
    private val okHttp: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(logger)

    //Create retrofit instance
    private val builder: Retrofit.Builder = Retrofit.Builder().baseUrl(BASE_URL ).addConverterFactory(
        GsonConverterFactory.create())
        .client(okHttp.build())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))

    //Create retrofit instance
    private val retrofit: Retrofit = builder.build()

    fun <T> builService(serviceType: Class<T>):T{
        return retrofit.create(serviceType)
    }
}