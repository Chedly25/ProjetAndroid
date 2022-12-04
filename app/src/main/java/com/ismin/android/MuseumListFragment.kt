package com.ismin.android
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.SupportMapFragment


private const val MUSEES = "musees"

class MuseumListFragment : Fragment() {
    private lateinit var museeAdapter: MuseeAdapter
    private lateinit var rcvMuseums: RecyclerView
    private var musees: ArrayList<Musee> = arrayListOf()
    private var favoris: ArrayList<Musee> = arrayListOf()



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