package com.example.user.screeningtest

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val preferences = this.getSharedPreferences("nameUser",0)

//        btnNext.setOnClickListener {
//
//            val editor = preferences?.edit()
//            editor?.putString("nameUser", name)
//            editor?.apply()
//            val intent = Intent(this, Main2Activity::class.java)
//            startActivity(intent)
//        }

        if(preferences.getString("nameUser", "Not Found") == ("Not Found")) {
            btnNext.setOnClickListener {
                val name = inputName.text.toString()
                val editor = preferences?.edit()
                editor?.putString("nameUser", name)
                editor?.apply()
                val intent = Intent(this, Main2Activity::class.java)
                startActivity(intent)
            }
        }else{
            val intent = Intent(this,Main2Activity::class.java)
            startActivity(intent)
        }



    }
}
