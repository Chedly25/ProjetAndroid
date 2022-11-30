package com.ismin.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MuseeAdapter(private var musees: List<Musee>) : RecyclerView.Adapter<MuseumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MuseumViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(
            R.layout.row_musee, parent,
            false
        )
        return MuseumViewHolder(row)
    }

    override fun onBindViewHolder(holder: MuseumViewHolder, position: Int) {
        val musee = musees[position]
        holder.latitude.text = musee.latitude.toString()
        holder.date_appellation.text = musee.date_appellation.toString()
        holder.region.text = musee.region
        holder.lieu.text = musee.lieu
        holder.telephone.text = musee.telephone
        holder.departement.text = musee.departement
        holder.url.text = musee.url
        holder.id.text = musee.id
        holder.nom.text = musee.nom
        holder.adresse.text = musee.adresse
        holder.longitude.text = musee.longitude.toString()
        holder.favori.text = musee.favori.toString()
    }

    override fun getItemCount(): Int {
        return musees.size
    }

    fun refreshData(allMusees: List<Musee>) {
        this.musees = allMusees;
    }
}