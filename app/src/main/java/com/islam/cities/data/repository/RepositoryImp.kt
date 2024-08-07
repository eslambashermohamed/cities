package com.islam.cities.data.repository

import android.util.Log
import com.islam.cities.utils.Parser
import com.islam.cities.data.model.CityModel
import com.islam.cities.data.model.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImp @Inject constructor(private val jsonParser: Parser): Repository {
    override suspend fun getListOfCities(): Flow<State<List<CityModel>>>? {
        return flow{
            emit(State.Loading())
            try {
                val data=jsonParser.readLocations()
                emit(State.Success(data!!))
            }catch (e:Exception){
               emit(State.Error("not found data"))
            }
        }



    }
}
