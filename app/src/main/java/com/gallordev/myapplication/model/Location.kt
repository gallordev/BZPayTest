package com.gallordev.myapplication.model

import com.google.gson.annotations.SerializedName

data class Location(
    val name: String,
    val region: String,
    val country: String,
    @SerializedName("localtime")
    val localTime: String
)
