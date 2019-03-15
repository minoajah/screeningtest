package com.example.user.screeningtest

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_event.*

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import kotlinx.android.synthetic.main.activity_main2.*

class EventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

//        val xRecyclerView: XRecyclerView
//        xRecyclerView = findViewById(R.id.mRecyclerView)

        supportActionBar?.title = "Event"
        xRecyclerView.layoutManager = LinearLayoutManager(this)



        val eventList = ArrayList<Event>()
        val events = Event("Synchronize Fest", "5 Oktober 2018", R.drawable.image1)
        val events2 = Event("Car Free Day", "28 Oktober 2018", R.drawable.image2)
        val events3 = Event("Binus Run", "4 November 2018", R.drawable.image3)
        val events4 = Event("Charity For Palu", "11 November 2018", R.drawable.image4)
        eventList.add(events)
        eventList.add(events2)
        eventList.add(events3)
        eventList.add(events4)

        val adapter = RVAdapterEvent(applicationContext, eventList)
        xRecyclerView.adapter = adapter

        xRecyclerView.setLoadingListener(object : XRecyclerView.LoadingListener{
            override fun onLoadMore() {

            }

            override fun onRefresh() {
                val handler = Handler()
                object : Runnable {
                    override fun run() {
                        handler.postDelayed(this,2)
                    }

                }
            }

        })


    }
}
