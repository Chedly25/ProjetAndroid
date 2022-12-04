package com.ismin.android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 * A simple [Fragment] subclass.
 * Use the [MapsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val MUSEES = "musees"

class MapsFragment : Fragment() {

    private lateinit var mMap: GoogleMap
    private lateinit var museumsMap: SupportMapFragment
    private val musees = Musees()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val callback = OnMapReadyCallback { mMap ->
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

        mMap.clear() //clear old markers

        val googlePlex = CameraPosition.builder()
            .target(LatLng(36.806389, 10.181667))
            .zoom(10f)
            .bearing(0f)
            .tilt(45f)
            .build()

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null)
        musees.getAllMuseums().forEach {
            val location = LatLng(it.longitude, it.latitude)
            mMap.addMarker(MarkerOptions().position(location).title(it.nom))
            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(0.0, 0.0))
                    .title("Spider Man")
            )
            /**
            }*/

        }
        /**override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_maps, container, false)
        museumsMap = (childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?)!!
        museumsMap.getMapAsync(callback)
        return rootView
        }*/

        /**override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        }*/
    }
    companion object {
        @JvmStatic
        fun newInstance(musees: ArrayList<Musee>) =
            MuseumListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(MUSEES, musees)
                }
            }
    }
}