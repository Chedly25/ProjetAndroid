package com.ismin.android
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), Communicator {

    private val museums = Musees()
    private var btn1: Button? = null
    private var btn2: Button? = null
    private var btn3: Button? = null
    private lateinit var adapter :MuseeAdapter
    private lateinit var mMap: GoogleMap
    private lateinit var mapFragment: MapsFragment
    private lateinit var museumListFragment: MuseumListFragment
    private lateinit var infoFragment : InfoFragment

    val SERVER_BASE_URL = "https://museums-cbjr.cleverapps.io/"
    val ListMusee : ArrayList<Musee> = arrayListOf()
    val retrofit = Retrofit.Builder()
        .baseUrl(SERVER_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val museumService = retrofit.create(MuseumService::class.java)

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = MuseeAdapter(/*this,*/ListMusee)
        btn1 = findViewById(R.id.btn1) as Button
        btn2 = findViewById(R.id.btn2) as Button
        btn3 = findViewById(R.id.btn3) as Button
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        //displayMapsFragment()
        //onDisplayMaps()

        btn1!!.setOnClickListener { onDisplayMuseumList() }

        btn2!!.setOnClickListener { onDisplayMaps() }

        btn3!!.setOnClickListener { displayInfoFragment() }
        //onDisplayMuseumList()
    }

    private fun displayMuseumListFragment() {
        museumListFragment = MuseumListFragment.newInstance(museums.getAllMuseums())
        supportFragmentManager.beginTransaction()
            .replace(R.id.a_main_frame_layout, museumListFragment)
            .commit()
    }

    private fun displayInfoFragment() {
        infoFragment = InfoFragment.newInstance(museums.getAllMuseums())
        supportFragmentManager.beginTransaction()
            .replace(R.id.a_main_frame_layout, infoFragment)
            .commit()
    }

    private fun displayMapsFragment() {
        mapFragment = MapsFragment.newInstance(museums.getAllMuseums())
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.a_main_frame_layout, mapFragment)
            .commit()
    }

    private fun displayMuseumFiltered(){
        val museumListFragment = MuseumListFragment.newInstance(adapter.getMainList())
        supportFragmentManager.beginTransaction()
            .replace(R.id.a_main_frame_layout, museumListFragment)
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
    private fun updateData(nom : String) {
        museumService.updateFavori(nom)
        .enqueue(object : Callback<Musee> {
            override fun onFailure(call: Call<Musee>, t: Throwable) {
                // Handle the error
            }

            override fun onResponse(call: Call<Musee>, response: Response<Musee>) {
                // Handle the response
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
                    if (getAllMuseums != null) {
                        for(i in 1 until getAllMuseums.size){
                            ListMusee.add(getAllMuseums.get(i))
                        }
                    }
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
        menuInflater.inflate(R.menu.menu_search, menu)
        val search : MenuItem? = menu?.findItem(R.id.searchIcon)
        val searchView :SearchView  = search?.actionView as SearchView

        searchView.queryHint = "Search something"
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                //updateMuseums(adapter.getMainList())
                displayMuseumFiltered()
                return false
            }
        })
        super.onCreateOptionsMenu(menu)
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
                favoris.forEach() {updateData(it.nom)}
                val intent = Intent(this, FavoriteMuseumsActivity::class.java)
                intent.putExtra("favoris", favoris)
                startActivity(intent)

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun notify(index:Int) {

        val intent = Intent(this, ShowActivity::class.java)

        intent.putExtra("nom",ListMusee.get(index).nom)
        intent.putExtra("adresse",ListMusee.get(index).adresse)
        intent.putExtra("url",ListMusee.get(index).url)
        intent.putExtra("telephone",ListMusee.get(index).telephone)
        intent.putExtra("lieu",ListMusee.get(index).lieu)
        intent.putExtra("date",ListMusee.get(index).date_appellation.toString())
        intent.putExtra("departement",ListMusee.get(index).departement)
        startActivity(intent)
        finish()
    }

}