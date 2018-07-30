package com.example.shafayat.sunpositionmvptutorial

import android.Manifest

object Constants {

    public val TIME_ZONE = "timezone"
    val LATITUDE: String = "latitude"
    val REQUEST_CODE_PERMISSION = 2
    val LONGITUDE: String = "longitude"
    val LOCATION_PERMS = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
}