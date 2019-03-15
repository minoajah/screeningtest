package com.example.user.screeningtest.Model

import com.google.gson.annotations.SerializedName

class GuestResponse<T> {
    @SerializedName("page")
    val page: Int = 0

    @SerializedName("total")
    val total: Int = 0

    @SerializedName("data")
    val data: List<T> = emptyList()
}