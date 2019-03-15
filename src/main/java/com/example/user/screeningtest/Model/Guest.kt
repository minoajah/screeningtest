package com.example.user.screeningtest.Model

import com.google.gson.annotations.SerializedName

data class Guest(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("last_name")
    val lastName: String
)