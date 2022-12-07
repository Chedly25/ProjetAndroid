package com.ismin.android

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MuseumViewHolder(rootView: View, listener: MuseeAdapter.onItemClickListener): ViewHolder(rootView) {
    var region = rootView.findViewById<TextView>(R.id.r_book_txv_region)
    var lieu = rootView.findViewById<TextView>(R.id.r_book_txv_lieu)
    var logo = rootView.findViewById<ImageView>(R.id.imageView)
    var nom = rootView.findViewById<TextView>(R.id.r_book_txv_nom)
    var favori = rootView.findViewById<TextView>(R.id.favori)
    val btnFavori = rootView.findViewById<ImageButton>(R.id.button3)

    init {
        itemView.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }
    }
}