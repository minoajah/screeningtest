package com.example.user.screeningtest

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val preferencesUser = this.getSharedPreferences("nameUser",0)
        val getName = preferencesUser.getString("nameUser", "Not Found")
        txtName.text = getName

        val preferencesEvent = this.getSharedPreferences("eventName", 0)
        val getEventName = "Event: ${preferencesEvent.getString("eventName", "Pilih Event")}"
        btnEvent.text = getEventName

        val preferencesGuest = this.getSharedPreferences("guestName", 0)
        val getGuestName = "Guest: ${preferencesGuest.getString("guestName", "Pilih Guest")}"
        btnGuest.text = getGuestName

        btnEvent.setOnClickListener{
            val intent = Intent(this,EventActivity::class.java)
            startActivity(intent)
        }
        btnGuest.setOnClickListener{
            val intent = Intent(this, GuestActivity::class.java)
            startActivity(intent)
        }
    }
}
