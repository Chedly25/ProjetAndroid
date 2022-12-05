package com.ismin.android
import FavoriteMuseumsActivity
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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

class MainActivity : AppCompatActivity() {

    private val museums = Musees()
    private var btn1: Button? = null
    private var btn2: Button? = null
    private lateinit var mMap: GoogleMap
    private lateinit var mapFragment: MapsFragment
    private lateinit var museumListFragment: MuseumListFragment
    val SERVER_BASE_URL = "https://museums-cbjr.cleverapps.io/"

    val retrofit = Retrofit.Builder()
        .baseUrl(SERVER_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val museumService = retrofit.create(MuseumService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn1 = findViewById(R.id.btn1) as Button
        btn2 = findViewById(R.id.btn2) as Button
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        //displayMapsFragment()
        //onDisplayMaps()
        btn1!!.setOnClickListener { onDisplayMuseumList() }

        btn2!!.setOnClickListener { onDisplayMaps() }

        //onDisplayMuseumList()
    }
    private fun displayMuseumListFragment() {
        museumListFragment = MuseumListFragment.newInstance(museums.getAllMuseums())
        supportFragmentManager.beginTransaction()
            .replace(R.id.a_main_frame_layout, museumListFragment)
            .commit()
    }

    private fun displayMapsFragment() {
        mapFragment = MapsFragment.newInstance(museums.getAllMuseums())
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.a_main_frame_layout, mapFragment)
            .commit()
    }

    fun showMap() {
        supportFragmentManager.beginTransaction()
            .show(mapFragment)
            .hide(museumListFragment)
            .commit()
    }

    // Show the list fragment
    fun showList() {
        supportFragmentManager.beginTransaction()
            .show(museumListFragment)
            .hide(mapFragment)
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
        displayMapsFragment()
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
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
            R.id.favori -> {
                var favoris = museumListFragment.getAdapter().getFavoris()
                val intent = Intent(this, FavoriteMuseumsActivity::class.java)
                intent.putExtra("favoris", favoris)
                startActivity(intent)

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}