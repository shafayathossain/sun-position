package com.example.shafayat.sunpositionmvptutorial

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.easywaylocation.EasyWayLocation.LOCATION_SETTING_REQUEST_CODE
import com.example.shafayat.sunpositionmvptutorial.Constants.LOCATION_PERMS
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainActivityInterface {

    var presenter : MainPresenterInterface? = null

    override fun showPosition(position : String) {
        positionTextView.text = position
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this, applicationContext)
        refreshButton.setOnClickListener { v ->
            run {

                presenter?.refreshSunPosition()

            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_SETTING_REQUEST_CODE)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(LOCATION_PERMS, LOCATION_SETTING_REQUEST_CODE)
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == LOCATION_SETTING_REQUEST_CODE)
            presenter?.sendResult(requestCode)
    }

    override fun onPause() {
        super.onPause()
        presenter?.detach()
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(LOCATION_PERMS[0]) == PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(LOCATION_PERMS[1]) == PackageManager.PERMISSION_GRANTED)
                presenter?.attach()
            else {
                requestPermissions(LOCATION_PERMS, LOCATION_SETTING_REQUEST_CODE)
            }
        }
    }

}
