package com.ismin.android

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MuseumViewHolder(rootView: View): ViewHolder(rootView) {
    //var latitude = rootView.findViewById<TextView>(R.id.r_book_txv_latitude)
    //var date_appellation = rootView.findViewById<TextView>(R.id.r_book_txv_dateappellation)
    var region = rootView.findViewById<TextView>(R.id.r_book_txv_region)
    var lieu = rootView.findViewById<TextView>(R.id.r_book_txv_lieu)
    var logo = rootView.findViewById<ImageView>(R.id.imageView)
    //var telephone = rootView.findViewById<TextView>(R.id.r_book_txv_telephone)
    //var departement = rootView.findViewById<TextView>(R.id.r_book_txv_departement)
    //var url = rootView.findViewById<TextView>(R.id.r_book_txv_url)
    //var id = rootView.findViewById<TextView>(R.id.r_book_txv_id)
    var nom = rootView.findViewById<TextView>(R.id.r_book_txv_nom)
    //var adresse = rootView.findViewById<TextView>(R.id.r_book_txv_adresse)
    //var longitude = rootView.findViewById<TextView>(R.id.r_book_txv_longitude)
    var favori = rootView.findViewById<TextView>(R.id.r_book_txv_favori)
}