package com.example.kshitijsih

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SafezoneFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SafezoneFragment : Fragment(),OnMapReadyCallback,OnMarkerClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_safezone, container, false)

        val options = GoogleMapOptions()
        options.mapType(GoogleMap.MAP_TYPE_NORMAL)
            .compassEnabled(false)
            .rotateGesturesEnabled(false)
            .tiltGesturesEnabled(false)
        val mapFragment= SupportMapFragment.newInstance(options)
        childFragmentManager
            .beginTransaction()
            .add(R.id.map_fragment_safezone, mapFragment)
            .commit()

        mapFragment.getMapAsync(this)
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SafezoneFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SafezoneFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val goa = LatLng( 15.496777,  73.827827)
        googleMap.addMarker(
            MarkerOptions()
                .position(goa)
                .title("Goa")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.avatar))
                .anchor(0.5f, 1F)
        )
        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng( 15.486777,  73.827827))
                .title("Goa")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.avatar))
                .anchor(0.5f, 1F)
        )
        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng( 15.496777,  73.825097))
                .title("Goa")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.avatar))
                .anchor(0.5f, 1F)
        )

        googleMap.addCircle(
            CircleOptions()
                .center(goa)
                .radius(500.0)
                .fillColor(0x22FF0000)
                .strokeWidth(1.0F)
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(goa))
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(15f))

        googleMap.setOnMarkerClickListener(this)
    }

    override fun onMarkerClick(p0: Marker): Boolean {
        val bottonSheetDialogFragment= EmergencyBottomFragment()
        bottonSheetDialogFragment.show(childFragmentManager,"BottomSheet")
        return true
    }
}