package com.example.shafayat.sunpositionmvptutorial

interface MainPresenterInterface {

    fun refreshSunPosition()
    fun sendResult(resultCode : Int)
    fun attach()
    fun detach()

}
