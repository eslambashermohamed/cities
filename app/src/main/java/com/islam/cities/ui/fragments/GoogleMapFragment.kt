package com.islam.cities.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.islam.cities.R
import com.islam.cities.databinding.FragmentGoogleMapBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GoogleMapFragment : Fragment(), OnMapReadyCallback {

    lateinit var binding: FragmentGoogleMapBinding
    private lateinit var mMap: GoogleMap
    private val args: GoogleMapFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_google_map, container, false)
        Log.i("MYCODE", isGooglePlayServicesAvailable(requireContext()).toString())
        return binding.root
    }

    private fun moveToLocation(lat: Double, lon: Double, name: String) {
        val location = LatLng(lat, lon)
        mMap.addMarker(MarkerOptions().position(location).title(name))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        moveToLocation(args.lat.toDouble(), args.lon.toDouble(), args.name)
        Log.i("MYCODE", "hello")
    }

    fun isGooglePlayServicesAvailable(context: Context): Boolean {
        val googleApiAvailability = GoogleApiAvailability.getInstance()
        return googleApiAvailability.isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS
    }

}