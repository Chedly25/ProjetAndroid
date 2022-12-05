package com.ismin.android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/**
 * A simple [Fragment] subclass.
 * Use the [InfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InfoFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_info, container, false)
        val urlInfo = rootView.findViewById<TextView>(R.id.url_info)
        val donneesInfo = rootView.findViewById<TextView>(R.id.donnees_info)
        val licencesInfo = rootView.findViewById<TextView>(R.id.licences_info)
        urlInfo.text =
            "Pour plus d'informations sur le dataset utilisé, voici l'url sur lequel se base notre application: \n\n" +
                    "https://data.culture.gouv.fr/explore/dataset/liste-et-localisation-des-musees-de-france/information/"
        donneesInfo.text = "Yo BG"
        licencesInfo.text = "hehe"
        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance(musees: ArrayList<Musee>) =
            InfoFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("musees", musees)
                }
            }
    }
}