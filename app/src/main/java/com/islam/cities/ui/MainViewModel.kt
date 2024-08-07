package com.islam.cities.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islam.cities.data.model.CityModel
import com.islam.cities.data.model.State
import com.islam.cities.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
   private val _list = MutableStateFlow<List<CityModel>>(emptyList())
   val list:StateFlow<List<CityModel>> =_list
    fun getListOfCities() {
        viewModelScope.launch(Dispatchers.IO) {
            var state = repository.getListOfCities()
            state?.collect { state ->
                if (state is State.Loading) {

                }
                if (state is State.Success) {
                    val data = state.data
                    _list.emit(data)
                }
                if (state is State.Error) {
                    throw Exception(state.message)
                }
            }
        }


    }
}
