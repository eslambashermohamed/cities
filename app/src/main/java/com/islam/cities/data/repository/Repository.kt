package com.islam.cities.data.repository

import com.islam.cities.data.model.CityModel
import com.islam.cities.data.model.State
import kotlinx.coroutines.flow.Flow

interface Repository {
     suspend fun getListOfCities(): Flow<State<String>>?
}