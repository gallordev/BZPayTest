package com.gallordev.myapplication.model

import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("temp_c")
    val tempC: Float = 0.0F
)