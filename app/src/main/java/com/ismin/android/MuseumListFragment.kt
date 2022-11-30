package com.ismin.android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val MUSEES = "musees"

class MuseumListFragment : Fragment() {
    private lateinit var museeAdapter: MuseeAdapter
    private lateinit var rcvMuseums: RecyclerView
    private var musees: ArrayList<Musee> = arrayListOf()
    val SERVER_BASE_URL = "https://museums-cbjr.cleverapps.io/musees/"

    val retrofit = Retrofit.Builder()
        .baseUrl(SERVER_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val museumService = retrofit.create(MuseumService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            musees = it.getSerializable(MUSEES) as ArrayList<Musee>
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        museumService.getMuseums()
            .enqueue(object : Callback<List<Musee>> {
                override fun onResponse(
                    call: Call<List<Musee>>,
                    response: Response<List<Musee>>
                ) {
                    musees = response.body() as ArrayList<Musee>
                    println(musees)
                }

                override fun onFailure(call: Call<List<Musee>>, t: Throwable) {
                    error("KO")

                }
            })
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_museum_list, container, false)

        museeAdapter = MuseeAdapter(musees)

        rcvMuseums = rootView.findViewById(R.id.f_museum_list_rcv_museums)
        rcvMuseums.adapter = museeAdapter

        val linearLayoutManager = LinearLayoutManager(context)
        rcvMuseums.layoutManager = linearLayoutManager

        return rootView
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