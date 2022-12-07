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
    var usingPage : Int = 0
    private lateinit var adapter :MuseeAdapter
    private  var favoris :ArrayList<Musee> = arrayListOf()
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
        adapter = MuseeAdapter(ListMusee,this)
        btn1 = findViewById(R.id.btn1) as Button
        btn2 = findViewById(R.id.btn2) as Button
        btn3 = findViewById(R.id.btn3) as Button

        onDisplayMuseumList()

        btn1!!.setOnClickListener {
            onDisplayMuseumList()
            usingPage =1}

        btn2!!.setOnClickListener {
            onDisplayMaps()
            usingPage=2 }

        btn3!!.setOnClickListener {
            usingPage =3
            displayInfoFragment() }
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
                    if (getAllMuseums != null) {
                        for(i in 1 until getAllMuseums.size){
                            ListMusee.add(getAllMuseums.get(i))
                            if(getAllMuseums.get(i).favori){
                                favoris.add(getAllMuseums.get(i))
                            }
                        }
                    }
                    getAllMuseums?.forEach { museums.addMusee(it) }
                    displayMuseumListFragment()
                }
                override fun onFailure(call: Call<List<Musee>>, t: Throwable) {
                    error("KO")
                }
            })
    }

    fun updateFavoris(name : String) {
        museumService.addOrRemoveFromFavorite(name)
            .enqueue(object :Callback<Musee>{
                override fun onResponse(call: Call<Musee>, response: Response<Musee>) {
                    val getMuseums: List<Musee>? = response.body() as ArrayList<Musee>

                }

                override fun onFailure(call: Call<Musee>, t: Throwable) {
                    println(t)
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
                displayMuseumFiltered()
                return false
            }
        })
        super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favori -> {
                val intent = Intent(this, FavoriteMuseumsActivity::class.java)
                intent.putExtra("favoris", favoris)
                startActivity(intent)
                true
            }
            R.id.refreshIcon->{
                if(usingPage==1){
                    onDisplayMuseumList()

                }
                else if(usingPage==2){
                    onDisplayMaps()

                }
                else if(usingPage==3){

                }

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
        //finish()
    }
    fun setFavoris(musee:Musee){
        favoris.add(musee)
    }
    fun removeFavoris(musee:Musee){
        favoris.remove(musee)
    }


}