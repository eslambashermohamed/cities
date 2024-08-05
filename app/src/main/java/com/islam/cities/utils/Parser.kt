package com.islam.cities.utils

import android.content.Context
import com.google.gson.stream.JsonReader
import com.islam.cities.data.model.CityModel
import com.islam.cities.data.model.Coord
import java.io.IOException
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Parser @Inject constructor(val context:Context){
    fun readLocations(): List<CityModel> {
        val locations = mutableListOf<CityModel>()

        context.assets.open("cities.json").use { inputStream ->
            val reader = JsonReader(InputStreamReader(inputStream, "UTF-8"))
            reader.use { jsonReader ->
                jsonReader.beginArray() // Start reading the JSON array
                while (jsonReader.hasNext()) {
                    locations.add(readLocation(jsonReader))
                }
                jsonReader.endArray() // End reading the JSON array
            }
        }

        return locations
    }
    @Throws(IOException::class)
    private fun readLocation(reader: JsonReader): CityModel {
        var country = ""
        var name = ""
        var id = 0
        var lon = 0.0
        var lat = 0.0

        reader.beginObject() // Start reading the JSON object
        while (reader.hasNext()) {
            when (reader.nextName()) {
                "country" -> country = reader.nextString()
                "name" -> name = reader.nextString()
                "_id" -> id = reader.nextInt()
                "coord" -> {
                    reader.beginObject() // Start reading the "coord" object
                    while (reader.hasNext()) {
                        when (reader.nextName()) {
                            "lon" -> lon = reader.nextDouble()
                            "lat" -> lat = reader.nextDouble()
                        }
                    }
                    reader.endObject() // End reading the "coord" object
                }
                else -> reader.skipValue() // Skip any other fields
            }
        }
        reader.endObject() // End reading the JSON object

        return CityModel(country, name, id, Coord(lon=lon,lat=lat))
    }
}