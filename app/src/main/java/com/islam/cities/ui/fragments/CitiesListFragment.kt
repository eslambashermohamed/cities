package com.islam.cities.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.islam.cities.R
import com.islam.cities.data.model.State
import com.islam.cities.databinding.FragmentCitiesListBinding
import com.islam.cities.ui.MainViewModel
import com.islam.cities.ui.MyRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class CitiesListFragment : Fragment() {
    lateinit var binding: FragmentCitiesListBinding
    lateinit var recyclerView: MyRecyclerView


    private val viewModel: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cities_list, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.getListOfCities()
        showCitiesInRecycler()
        search()

        return binding.root
    }

    fun showCitiesInRecycler() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                if (state is State.Success) {
                    recyclerView = MyRecyclerView(state.data)
                    withContext(Dispatchers.Main) {
                        binding.recyclerview.adapter = recyclerView
                    }
                }
            }
        }
    }
    fun search(){
        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence?, i: Int, i1: Int, i2: Int) {
                recyclerView.filter.filter(charSequence)
            }

            override fun afterTextChanged(editable: Editable?) {}
        })
    }

}