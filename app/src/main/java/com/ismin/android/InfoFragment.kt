package com.ismin.android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

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
        val image1 = rootView.findViewById<ImageView>(R.id.imageView3)
        val image2 = rootView.findViewById<ImageView>(R.id.imageView4)
        val image3 = rootView.findViewById<ImageView>(R.id.imageView5)
        urlInfo.text =
            "Pour plus d'informations sur le dataset utilisé, voici l'url sur lequel se base notre application: \n\n" +
                    "https://data.culture.gouv.fr/explore/dataset/liste-et-localisation-des-musees-de-france/information/"
        donneesInfo.text = "   Explication des données :  \n\n" +"• Nom : le nom officiel du musée\n" +
                "• Lieu : le nom du bâtiment dans lequel se trouve le musée\n"+"• Adresse : l'adresse du musée\n"+
                "• Département : le département dans lequel se trouve le musée\n"+
                "• La date d'appellation : la date à laquelle le musée a intégré le groupe <<Musées de France>>\n" +
                "• Région : la région dans laquelle se trouve le musée\n" +
                "• Latitude/Longitude : les coordonnées géographiques du musée\n"+
                "• Téléphone : le numéro de téléphone du musée\n"+
                "• URL: le site web du musée pour avoir plus d'informations\n"
        licencesInfo.text = "   Librairies : \n\n"+ "• retrofit2\n" +"• com.google.android.gms.maps\n" + "• Picasso\n" + "• Gson\n" + "• RecyclerView"
        val url1 = "https://www.culture.gouv.fr/var/culture/storage/images/_aliases/reference/7/3/0/4/3164037-1-fre-FR/LogoMCHP.PNG"
        Picasso.get().load(url1).into(image1)
        val url2 = "https://www.culture.gouv.fr/var/culture/storage/images/_aliases/medium/media/aides-demarches/protections-labels-appellations/logo-musee-de-france/158806-1-fre-FR/Logo-musee-de-France.jpg"
        Picasso.get().load(url2).into(image2)
        val url3 = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/55/Logo_Mines_Saint-%C3%89tienne.svg/langfr-225px-Logo_Mines_Saint-%C3%89tienne.svg.png"
        Picasso.get().load(url3).into(image3)
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