package com.islam.cities.data.model

sealed class State<out T> {
    class Loading():State<Nothing>()
    class Success<T>(val  data:T):State<T>()
    class Error<T>(val message:String):State<T>()
}