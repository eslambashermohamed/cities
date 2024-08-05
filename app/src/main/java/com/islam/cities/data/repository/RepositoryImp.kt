package com.islam.cities.data.repository

import android.util.Log
import com.islam.cities.data.JsonParser
import com.islam.cities.data.Parser
import com.islam.cities.data.model.CityModel
import com.islam.cities.data.model.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryImp(val jsonParser: Parser): Repository {
    override suspend fun getListOfCities(): Flow<State<List<CityModel>>>? {
        return flow{
            emit(State.Loading())
            try {
                val data=jsonParser.readLocations()
                Log.i("MYCODE",data.toString())
                emit(State.Success(data!!))
            }catch (e:Exception){
               emit(State.Error("not found data"))
            }
        }



    }
}
