package com.islam.cities.data.model

import com.google.gson.annotations.SerializedName

data class Coord(
    @SerializedName("lon")
    val lon: Double? = null,
    @SerializedName("lat")
    val lat: Double? = null
)
/*
"coord":{
    "lon":34.283333,
    "lat":44.549999
}
*/
