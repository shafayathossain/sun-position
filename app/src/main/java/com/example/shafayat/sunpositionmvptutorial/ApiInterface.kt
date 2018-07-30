package com.example.shafayat.sunpositionmvptutorial

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiInterface {

    @GET("sunposition/get-altitude/")
    fun getAltitude(@QueryMap data : HashMap<String, String>) : Call<AltitudeResponse>
}
