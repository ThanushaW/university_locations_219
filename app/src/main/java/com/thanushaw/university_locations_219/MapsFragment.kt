package com.thanushaw.university_locations_219

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.viewbinding.ViewBindings

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.thanushaw.university_locations_219.apis.University

class MapsFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        var loc = LatLng(-34.0, 151.0)
        var name = "unknown"
        setFragmentResultListener("requestKey"){_,bundle->
            name = bundle.get("selectedNameKey") as String
            val data = bundle.get("data") as List<University>

            data.forEach {
                if(it.name == name){
                    loc = it.coordinates
//                    Toast.makeText(this.context, "Clicked on ${it.toString()}", Toast.LENGTH_LONG).show()
                }
            }

        }
        googleMap.setMinZoomPreference(17.0f)
        googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        googleMap.addMarker(MarkerOptions().position(loc).title("Marker in $name"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(loc))
        (activity as AppCompatActivity).supportActionBar?.title = name


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}