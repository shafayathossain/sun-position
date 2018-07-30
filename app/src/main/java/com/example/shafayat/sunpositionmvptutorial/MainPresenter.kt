package com.example.shafayat.sunpositionmvptutorial

import android.content.Context
import android.view.View
import java.util.*

class MainPresenter(var mainActivityInterface: MainActivityInterface, context : Context) : MainPresenterInterface {

    var datasource : DataSource = DataSource(context)

    override fun attach() {
        datasource.easyWayLocation.beginUpdates()
    }

    override fun sendResult(requestCode : Int) {
        datasource.easyWayLocation.onActivityResult(requestCode)
    }

    override fun refreshSunPosition() {

        datasource.updateSunPosition(getTimeZone())
        if(AltitudeResponse.getInstance().altitude!=-1.0)
            mainActivityInterface.showPosition(AltitudeResponse.getInstance().altitude.toString())
        else
            mainActivityInterface.showPosition(AltitudeResponse.getInstance().error)
    }


    fun getTimeZone() : Int {
        var a = GregorianCalendar.getInstance().timeZone.rawOffset/(1000*60*60)
        return a
    }

    override fun detach() {
        datasource.easyWayLocation.endUpdates()
    }

}

