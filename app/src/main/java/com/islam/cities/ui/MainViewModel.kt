package com.islam.cities.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islam.cities.data.model.CityModel
import com.islam.cities.data.model.State
import com.islam.cities.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val list = MutableStateFlow<List<CityModel>>(emptyList())

    fun getListOfCities() {
        viewModelScope.launch(Dispatchers.IO) {
            var state = repository.getListOfCities()
            state?.collect { state ->
                if (state is State.Loading) {

                }
                if (state is State.Success) {
                    val data = state.data
                    list.emit(data)


                }
                if (state is State.Error) {
                    throw Exception(state.message)
                }
            }
        }


    }
}
