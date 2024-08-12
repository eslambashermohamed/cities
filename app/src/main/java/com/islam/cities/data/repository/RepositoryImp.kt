package com.islam.cities.data.repository

import android.content.Context
import com.islam.cities.data.model.CityModel
import com.islam.cities.data.model.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImp @Inject constructor(
        private val context: Context
) : Repository {

    override suspend fun getListOfCities(): Flow<State<String>>? {
        return flow {
            emit(State.Loading())
            try {
                val data =getJsonData()
                emit(State.Success(data!!))
            } catch (e: Exception) {
                emit(State.Error("not found data"))
            }
        }


    }

    fun getJsonData(): String {
        var json: String
        try {
            context.assets.open("cities.json").use { inputStream ->
                BufferedReader(InputStreamReader(inputStream)).use { reader ->
                    val stringBuilder = StringBuilder()
                    reader.lineSequence().forEach { line ->
                        stringBuilder.append(line)
                    }
                    json = stringBuilder.toString()
                }
            }
        } catch (ex: Exception) {
            throw Exception("NOT FOUND cities FILE")
        }
        return json
    }
}
