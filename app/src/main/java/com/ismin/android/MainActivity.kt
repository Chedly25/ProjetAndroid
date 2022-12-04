package com.ismin.android
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), MuseumCreator {

    private val museums = Musees()
    private lateinit var mMap: GoogleMap
    val SERVER_BASE_URL = "https://museums-cbjr.cleverapps.io/"

    val retrofit = Retrofit.Builder()
        .baseUrl(SERVER_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val museumService = retrofit.create(MuseumService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        //displayMapsFragment()
        onDisplayMaps()
        //onDisplayMuseumList()
    }
    private fun displayMuseumListFragment() {
        val museumListFragment = MuseumListFragment.newInstance(museums.getAllMuseums())
        supportFragmentManager.beginTransaction()
            .replace(R.id.a_main_frame_layout, museumListFragment)
            .commit()
    }

    private fun displayMapsFragment() {
        val mapFragment = SupportMapFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.a_main_frame_layout, mapFragment)
            .commit()
    }

    private fun onDisplayMaps() {
        museumService.getMuseums()
            .enqueue(object : Callback<List<Musee>> {
                override fun onResponse(
                    call: Call<List<Musee>>,
                    response: Response<List<Musee>>
                ) {
                    val getAllMuseums: List<Musee>? = response.body() as ArrayList<Musee>
                    println(getAllMuseums)
                    getAllMuseums?.forEach { museums.addMusee(it) }
                    val mapFragment =
                        supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
                    mapFragment?.getMapAsync { mMap ->
                        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

                        mMap.clear() //clear old markers

                        val googlePlex = CameraPosition.builder()
                            .target(LatLng( 36.806389, 10.181667))
                            .zoom(10f)
                            .bearing(0f)
                            .tilt(45f)
                            .build()
                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null)

                        if (getAllMuseums != null) {
                            getAllMuseums.forEach {
                                val location = LatLng(it.latitude, it.longitude)
                                mMap.addMarker(MarkerOptions().position(location).title(it.nom))
                            }
                        }

                    }
                }

                override fun onFailure(call: Call<List<Musee>>, t: Throwable) {
                    error("KO")

                }
            })

    }
    private fun onDisplayMuseumList() {
        museumService.getMuseums()
            .enqueue(object : Callback<List<Musee>> {
                override fun onResponse(
                    call: Call<List<Musee>>,
                    response: Response<List<Musee>>
                ) {
                    val getAllMuseums: List<Musee>? = response.body() as ArrayList<Musee>
                    println(getAllMuseums)
                    getAllMuseums?.forEach { museums.addMusee(it) }
                    displayMuseumListFragment()
                }

                override fun onFailure(call: Call<List<Musee>>, t: Throwable) {
                    error("KO")

                }
            })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_clean -> {
                museums.clean()
                displayMuseumListFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onMuseumCreated(musee: Musee) {
        displayMuseumListFragment()
    }
    /**override fun onMapReady(googleMap: GoogleMap) {
        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(0.0, 0.0))
                .title("Marker")
        )
    }*/
   /**override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        // Add a marker in Sydney and move the camera
        museumService.getMuseums()
            .enqueue(object : Callback<List<Musee>> {
                override fun onResponse(
                    call: Call<List<Musee>>,
                    response: Response<List<Musee>>
                ) {
                    val getAllMuseums: List<Musee>? = response.body() as ArrayList<Musee>
                    println(getAllMuseums)
                    getAllMuseums?.forEach { museums.addMusee(it) }
                    if (getAllMuseums != null) {
                        for(musee in getAllMuseums){
                            val coor = LatLng(musee.longitude, musee.latitude)
                            mMap.addMarker(MarkerOptions().position(coor).title("Marker in Sydney"))
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(coor))
                        }
                    }
                }

                override fun onFailure(call: Call<List<Musee>>, t: Throwable) {
                    error("KO")

                }
            })

    }*/
}