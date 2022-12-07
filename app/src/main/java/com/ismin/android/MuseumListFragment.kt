package com.ismin.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


private const val MUSEES = "musees"

class MuseumListFragment : Fragment() {
    private lateinit var museeAdapter: MuseeAdapter
    private lateinit var rcvMuseums: RecyclerView
    lateinit var communicator : Communicator
    private var musees: ArrayList<Musee> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            musees = it.getSerializable(MUSEES) as ArrayList<Musee>
        }

    }
    fun getAdapter() : MuseeAdapter {
        return museeAdapter
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_museum_list, container, false)

        museeAdapter = MuseeAdapter(musees, this.context as MainActivity)
        rcvMuseums = rootView.findViewById(R.id.f_museum_list_rcv_museums)
        rcvMuseums.adapter = museeAdapter
        museeAdapter.setOnItemClickListener(object : MuseeAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                communicator = activity as Communicator
                communicator.notify(position)

            }

        })
        val linearLayoutManager = LinearLayoutManager(context)
        rcvMuseums.layoutManager = linearLayoutManager
        rcvMuseums.addItemDecoration(
            DividerItemDecoration(
                rcvMuseums.getContext(),
                DividerItemDecoration.VERTICAL
            )
        )
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