package com.islam.cities.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.islam.cities.R
import com.islam.cities.data.JsonParser
import com.islam.cities.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val jsonPair = JsonParser()
        Log.i("MYCODE",jsonPair.loadCities(this).toString())

    }
}