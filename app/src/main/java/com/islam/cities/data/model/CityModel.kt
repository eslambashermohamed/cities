package com.islam.cities.data.model

import com.google.gson.annotations.SerializedName

data class CityModel(
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("_id")
    val _id: Int? = null,
    @SerializedName("coord")
    val coord: Coord

)
