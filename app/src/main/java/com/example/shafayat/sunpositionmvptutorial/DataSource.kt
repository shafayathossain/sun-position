package com.example.shafayat.sunpositionmvptutorial

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.content.PermissionChecker.checkSelfPermission
import com.example.easywaylocation.EasyWayLocation
import com.example.easywaylocation.Listener
import com.example.shafayat.sunpositionmvptutorial.Constants.LOCATION_PERMS
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class DataSource(var context: Context) : Listener {

    lateinit var easyWayLocation: EasyWayLocation
    lateinit var retrofitApiInterface: ApiInterface
    var latitude : Double = 0.0
    var longitude : Double = 0.0

    init {
        easyWayLocation = EasyWayLocation(context)
        easyWayLocation.setListener(this)
        retrofitApiInterface = RetrofitApiClient.getClient().create(ApiInterface::class.java)
    }

    override fun locationCancelled() {

    }

    override fun locationOn() {
        if (hasPermission()) {
            easyWayLocation.beginUpdates()
        }
        latitude = easyWayLocation.latitude
        longitude = easyWayLocation.longitude

    }



    private fun hasPermission(): Boolean {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermission(context, LOCATION_PERMS[0]) == PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(context, LOCATION_PERMS[1]) == PackageManager.PERMISSION_GRANTED
        } else
            return true
    }



    override fun onPositionChanged() {
        latitude = easyWayLocation.latitude
        longitude = easyWayLocation.longitude
    }

    fun updateSunPosition(timezone : Int ) {

        var data: HashMap<String, String> = HashMap()
        data.put(Constants.TIME_ZONE, timezone.toString())
        data.put(Constants.LATITUDE, latitude.toString())
        data.put(Constants.LONGITUDE, longitude.toString())
        val call: Call<AltitudeResponse> = retrofitApiInterface.getAltitude(data)
        call.enqueue(object : Callback<AltitudeResponse> {

            override fun onResponse(call: Call<AltitudeResponse>, response: Response<AltitudeResponse>) {
                if (response.body() != null){
                    AltitudeResponse.getInstance().altitude = response.body()!!.altitude
                    AltitudeResponse.getInstance().error = response.body()!!.error
                }
            }

            override fun onFailure(call: Call<AltitudeResponse>, t: Throwable) {
                AltitudeResponse.getInstance().error = t.message!!
            }
        })
    }

}