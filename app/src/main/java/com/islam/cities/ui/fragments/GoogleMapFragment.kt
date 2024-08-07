package com.islam.cities.ui.fragments

import android.content.Context.MODE_PRIVATE
import android.graphics.Rect
import android.location.GpsStatus
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.model.Marker
import com.islam.cities.R
import com.islam.cities.databinding.FragmentGoogleMapBinding
import dagger.hilt.android.AndroidEntryPoint
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration
import org.osmdroid.events.MapListener
import org.osmdroid.events.ScrollEvent
import org.osmdroid.events.ZoomEvent
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

@AndroidEntryPoint
class GoogleMapFragment : Fragment(), MapListener, GpsStatus.Listener {

    lateinit var binding: FragmentGoogleMapBinding
    lateinit var mMap: MapView
    lateinit var controller: IMapController;
    private val args: GoogleMapFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_google_map, container, false)
        mapConviguration()
        initMapView()
        return binding.root
    }

    override fun onScroll(event: ScrollEvent?): Boolean {
        return true
    }

    override fun onZoom(event: ZoomEvent?): Boolean {
        return false;
    }

    override fun onGpsStatusChanged(event: Int) {}
    fun mapConviguration() {
        Configuration.getInstance().load(
            requireContext(),
            activity?.getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE)
        )
    }

    fun initMapView() {
        mMap = binding.osmmap
        mMap.mapCenter
        mMap.setMultiTouchControls(true)
        mMap.getLocalVisibleRect(Rect())
        controller = mMap.controller
        controller.setZoom(6.0)
        val gPt = GeoPoint(args.lat.toDouble(), args.lon.toDouble());
        controller.setCenter(gPt);
        val marker = org.osmdroid.views.overlay.Marker(mMap)
        marker.position = gPt
        marker.title = args.name
        mMap.overlays.add(marker)
    }

}





