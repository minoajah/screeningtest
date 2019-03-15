package com.example.user.screeningtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.example.user.screeningtest.Model.Guest
import com.example.user.screeningtest.Model.GuestResponse
import com.example.user.screeningtest.service.ApiClient
import com.example.user.screeningtest.service.ApiInterface
import com.jcodecraeer.xrecyclerview.XRecyclerView
import kotlinx.android.synthetic.main.activity_guest.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class GuestActivity : AppCompatActivity() {

//    private val guestList: <Guest> = mutableListOf()
//    private lateinit var guestAdapter: GuestAdapter
    lateinit var guestAdapter: GuestAdapter
//    private val TAG : String = GuestActivity::class.java.canonicalName
    var page = 1
    var limit = 5
    var totalLoaded = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest)

        supportActionBar?.title = "Guest"
        guestAdapter = GuestAdapter(this)
        xRecyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
        xRecyclerView.adapter = guestAdapter

        val apiInterface = ApiClient.getClient().create(ApiInterface::class.java)


        apiInterface.getGuest(page, limit).enqueue(object : Callback<GuestResponse<Guest>>{
            override fun onFailure(call: Call<GuestResponse<Guest>>, t: Throwable) {

            }
            override fun onResponse(call: Call<GuestResponse<Guest>>, response: Response<GuestResponse<Guest>>) {
                if (response.isSuccessful) {
                    Log.d("tag", "responsennya ${response.body()?.data?.size.toString()}")
                    response.body()?.data.let { it ->
                        guestAdapter.setGuestListItems(it!!)
                    }
                }
            }
        })

        xRecyclerView.setLoadingListener(object : XRecyclerView.LoadingListener{
            override fun onLoadMore() {
                limit += 5
                Handler().postDelayed(object : Runnable{
                    override fun run() {
                        if (applicationContext != null){
                            apiInterface.getGuest(page, limit).enqueue(object : Callback<GuestResponse<Guest>>{
                                override fun onFailure(call: Call<GuestResponse<Guest>>, t: Throwable) {

                                }
                                override fun onResponse(call: Call<GuestResponse<Guest>>, response: Response<GuestResponse<Guest>>) {
                                    if (response.isSuccessful) {
                                        Log.d("tag", "responsennya ${response.body()?.data?.size.toString()}")
                                        totalLoaded = response.body()?.data?.size!!.toInt()
                                        response.body()?.data.let { it ->
                                            guestAdapter.setGuestListItems(it!!)
                                        }
                                    }
                                }
                            })
                        }
                        xRecyclerView.loadMoreComplete()
                        xRecyclerView.refreshComplete()
                        guestAdapter.notifyDataSetChanged()
                    }
                }, 1000)
            }

            override fun onRefresh() {
                limit = 5
                apiInterface.getGuest(page, limit).enqueue(object : Callback<GuestResponse<Guest>>{
                    override fun onFailure(call: Call<GuestResponse<Guest>>, t: Throwable) {

                    }
                    override fun onResponse(call: Call<GuestResponse<Guest>>, response: Response<GuestResponse<Guest>>) {
                        if (response.isSuccessful) {
                            Log.d("tag", "responsennya ${response.body()?.data?.size.toString()}")
                            totalLoaded = response.body()?.data?.size!!.toInt()
                            response.body()?.data.let { it ->
                                guestAdapter.setGuestListItems(it!!)
                            }
                        }
                    }
                })
                xRecyclerView.refreshComplete()
                guestAdapter.notifyDataSetChanged()
            }
        })
    }
}
