package com.example.user.screeningtest.service


import com.example.user.screeningtest.Model.Guest
import com.example.user.screeningtest.Model.GuestResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("api/users")
    fun getGuest(@Query("page") page: Int,
                 @Query("per_page") limit: Int) : Call<GuestResponse<Guest>>
}