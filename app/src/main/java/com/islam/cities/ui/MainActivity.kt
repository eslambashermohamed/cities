package com.islam.cities.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.islam.cities.R
import com.islam.cities.data.Parser
import com.islam.cities.data.repository.RepositoryImp
import com.islam.cities.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var recyclerView: MyRecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel: MainViewModel by viewModels() {
            VeiwModelFactory(RepositoryImp(Parser(this)))
        }
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        GlobalScope.launch {
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