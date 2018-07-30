package com.example.shafayat.sunpositionmvptutorial

import com.google.gson.annotations.SerializedName

class AltitudeResponse {

    @SerializedName("error")
    var error : String = ""

    @SerializedName("altitude")
    var altitude : Double = -1.0


    companion object {
        private var altitudeResponse : AltitudeResponse? = null
        fun getInstance(): AltitudeResponse {
            if (altitudeResponse == null) {
                altitudeResponse = AltitudeResponse()
            }
            return altitudeResponse as AltitudeResponse
        }
    }
}