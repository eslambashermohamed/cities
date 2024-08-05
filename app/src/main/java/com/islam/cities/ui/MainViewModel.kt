package com.islam.cities.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.islam.cities.data.model.CityModel
import com.islam.cities.data.model.State
import com.islam.cities.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {
    val search = MutableStateFlow("")
    val list = MutableStateFlow<List<CityModel>>(emptyList())

    init {
        getListOfCities()

    }

    fun getListOfCities() {
        viewModelScope.launch(Dispatchers.IO) {
            var state = repository.getListOfCities()
            state?.collect { state ->
                if (state is State.Loading) {

                }
                if (state is State.Success) {
                     val data = state.data
                    list.value=data


                }
                if (state is State.Error) {
                    throw Exception(state.message)
                }
            }
        }


    }
}

class VeiwModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == MainViewModel::class.java) {
            return MainViewModel(repository) as T
        }
        throw Throwable("un Nown")
    }
}