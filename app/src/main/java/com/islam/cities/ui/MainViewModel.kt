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

    private val _state = MutableStateFlow<State<List<CityModel>>>(State.Loading())
    val state: StateFlow<State<List<CityModel>>> = _state

    init {
        getListOfCities()
    }

    fun getListOfCities() {
        viewModelScope.launch(Dispatchers.IO) {
            var state = repository.getListOfCities()
            state?.collect { s ->
                if (s is State.Loading) {
                    _state.emit(State.Loading())
                }
                if (s is State.Success) {
                    val data = s.data
                    _state.emit(State.Success(data))
                }
                if (s is State.Error) {
                    _state.emit(State.Error("Error"))
                }
            }
        }
    }
}
