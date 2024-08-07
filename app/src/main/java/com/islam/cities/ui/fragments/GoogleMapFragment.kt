package com.islam.cities.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.islam.cities.R
import com.islam.cities.data.model.CityModel
import com.islam.cities.databinding.FragmentGoogleMapBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GoogleMapFragment : Fragment() , OnMapReadyCallback {

    lateinit var binding: FragmentGoogleMapBinding
    private lateinit var mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_google_map, container, false)
       // val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
       // mapFragment.getMapAsync(this)
        return binding.root
    }
    private fun moveToLocation(city: CityModel) {
        val location = LatLng(city.coord.lat!!, city.coord.lon!!)
        mMap.addMarker(MarkerOptions().position(location).title("${city.name}, ${city.country}"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
    }


}