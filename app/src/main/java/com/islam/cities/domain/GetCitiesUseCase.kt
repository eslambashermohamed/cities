package com.islam.cities.domain

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.islam.cities.data.model.CityModel
import com.islam.cities.data.model.State
import com.islam.cities.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCitiesUseCase @Inject constructor(
      private val repository: Repository
) {

    suspend fun getCities(): Flow<State<List<CityModel>>>? {
        return flow {
            emit(State.Loading())
            try {
                emit(State.Success(getParse()))
            } catch (ex: Exception) {
              emit(State.Error("Can't parse json"))
            }
        }

    }

    suspend fun getParse(): List<CityModel> {
        val gson = Gson()
        val cityListType = object : TypeToken<List<CityModel>>() {}.type
        var list: List<CityModel> = emptyList()
        repository.getListOfCities()?.collect {
            if (it is State.Success) {
                list = gson.fromJson(it.data, cityListType)
            }
        }
        return list
    }
}