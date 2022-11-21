package com.example.intentdemo

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.CallLog
import android.provider.ContactsContract
import android.provider.MediaStore
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class ActivityA : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpPermissions()
        initViews() // create a separate function to use outside of the main class
    }
// find the buttons you used to set a click listener later
    private fun initViews() {
        val buttonNext = findViewById<Button>(R.id.btnMoveToB)
        val buttonCall = findViewById<Button>(R.id.btnMakeCall)
        val buttonFindCallLog = findViewById<Button>(R.id.btnOpenCallLog)
        val buttonFindContacts = findViewById<Button>(R.id.btnOpenContacts)
        val buttonOpenBrowser = findViewById<Button>(R.id.btnOpenBrowser)
        val buttonOpenGallery = findViewById<Button>(R.id.btnOpenGallery)
        val buttonOpenCamera = findViewById<Button>(R.id.btnOpenCamera)
        val buttonOpenAlarm = findViewById<Button>(R.id.btnOpenAlarm)
        val buttonOpenMaps = findViewById<Button>(R.id.btnOpenMaps)




        buttonNext.setOnClickListener {
            // explicit intent here
            startActivity(Intent(this, ActivityB::class.java)) // telling it to move to the next screen
        }

    // set the listeners for each button
        buttonCall.setOnClickListener {intentToMakeCall()}
        buttonFindCallLog.setOnClickListener{ intentToOpenCallLog()}
        buttonFindContacts.setOnClickListener{intentToFindContacts()}
        buttonOpenBrowser.setOnClickListener{intentToOpenBrowser()}
        buttonOpenGallery.setOnClickListener{intentToOpenGallery()}
        buttonOpenCamera.setOnClickListener{intentToOpenCamera()}
        buttonOpenAlarm.setOnClickListener{intentToOpenAlarm()}
        buttonOpenMaps.setOnClickListener{intentToOpenMaps()}
    }

    private fun intentToMakeCall() {
        val phoneNumber = "+17084203455"
            val intent = Intent(Intent.ACTION_CALL, Uri.fromParts("tel", phoneNumber, null))
            startActivity(intent)
    }


    private fun intentToOpenMaps() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("geo:41.70065,-87.79794")
        startActivity(intent)
    }

    private fun intentToOpenAlarm() {
        val intent = Intent(AlarmClock.ACTION_SHOW_ALARMS)
        startActivity(intent)
    }

    private fun intentToOpenCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivity(intent)
    }

    private fun intentToOpenGallery() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("content://media/external/media/")
        startActivity(intent)
    }

    private fun intentToOpenBrowser() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://www.google.com")
        startActivity(intent)
    }

    private fun intentToFindContacts() {
    val intent = Intent(Intent.ACTION_PICK)
        intent.type = ContactsContract.Contacts.CONTENT_TYPE
        startActivity(intent)
    }
    private fun intentToOpenCallLog(){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.type = CallLog.Calls.CONTENT_TYPE
        startActivity(intent)
    }

    private fun setUpPermissions() {
        val permission =
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            makePermissionRequest()
        }
    }
    private fun makePermissionRequest(){
        ActivityCompat.requestPermissions(
            this, arrayOf(android.Manifest.permission.CALL_PHONE), Call_Request_Code
        )

    }


    companion object{
        const val Call_Request_Code = 100
    }

}





