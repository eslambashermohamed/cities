package com.islam.cities.data

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.islam.cities.data.model.CityModel
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class JsonParser {
    fun loadCities(context: Context): List<CityModel>? {
        val json: String

        return try {
            context.assets.open("cities.json").use { inputStream ->
                BufferedReader(InputStreamReader(inputStream)).use { reader ->
                    val stringBuilder = StringBuilder()
                    reader.lineSequence().forEach { line ->
                        stringBuilder.append(line)
                    }
                    json = stringBuilder.toString()
                }
            }

            // Use Gson to parse the JSON into a list of City objects
            val gson = Gson()
            val cityListType = object : TypeToken<List<CityModel>>() {}.type
            gson.fromJson(json, cityListType)

        } catch (e: IOException) {
            Log.e("JsonParser", "Error reading JSON file", e)
            null // Return null in case of an error
        }
    }
}