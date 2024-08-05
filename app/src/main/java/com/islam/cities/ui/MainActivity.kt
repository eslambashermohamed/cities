package com.islam.cities.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.islam.cities.R
import com.islam.cities.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var recyclerView: MyRecyclerView
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel.getListOfCities()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
       lifecycleScope.launch {
            viewModel.list.collect { data ->
                recyclerView = MyRecyclerView(data)
                withContext(Dispatchers.Main) {
                    binding.recyclerview.adapter = recyclerView
                }
            }
        }
        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence?, i: Int, i1: Int, i2: Int) {
                recyclerView.filter.filter(charSequence)
            }

            override fun afterTextChanged(editable: Editable?) {}
        })

    }
}