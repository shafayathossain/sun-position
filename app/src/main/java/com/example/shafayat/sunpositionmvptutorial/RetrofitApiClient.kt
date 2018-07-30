package com.example.shafayat.sunpositionmvptutorial

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitApiClient {

    private val BASE_URL: String = "http://shafayatsnest.com"
    private var retrofit: Retrofit? = null
    private val gson: Gson = GsonBuilder().setLenient().create()

    fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
        }
        return retrofit!!
    }
}