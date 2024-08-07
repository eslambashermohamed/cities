package com.islam.cities.data.model

import com.google.gson.annotations.SerializedName

data class Coord(
    @SerializedName("lon")
    val lon: Float? = null,
    @SerializedName("lat")
    val lat: Float? = null
)
