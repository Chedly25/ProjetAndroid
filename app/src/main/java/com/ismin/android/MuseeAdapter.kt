package com.ismin.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

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
        //holder.latitude.text = musee.latitude.toString()
        //holder.date_appellation.text = musee.date_appellation.toString()
        holder.region.text = musee.region
        holder.lieu.text = musee.lieu
        when (holder.region.text) {
            "Auvergne-Rhône-Alpes" -> {val url = "https://www.regions-et-departements.fr/images/logos-regions/thumbs/logo-auvergne-rhone-alpes.png"
            Picasso.get().load(url).into(holder.logo);}
            "Bourgogne-Franche-Comté" -> {val url = "https://www.regions-et-departements.fr/images/logos-regions/thumbs/logo-bourgogne-franche-comte.png"
                Picasso.get().load(url).into(holder.logo);}
            "Bretagne" -> {val url = "https://www.regions-et-departements.fr/images/logos-regions/thumbs/logo-bretagne.png"
                Picasso.get().load(url).into(holder.logo);}
            "Centre-Val de Loire" -> {val url = "https://www.regions-et-departements.fr/images/logos-regions/thumbs/logo-centre-val-de-loire.png"
                Picasso.get().load(url).into(holder.logo);}
            "Corse" -> {val url = "https://www.regions-et-departements.fr/images/logos-regions/thumbs/logo-corse.png"
                Picasso.get().load(url).into(holder.logo);}
            "Grand Est" -> {val url = "https://www.regions-et-departements.fr/images/logos-regions/thumbs/logo-grand-est.png"
                Picasso.get().load(url).into(holder.logo);}
            "Hauts-de-France" -> {val url = "https://www.regions-et-departements.fr/images/logos-regions/thumbs/logo-hauts-de-france.png"
                Picasso.get().load(url).into(holder.logo);}
            "Île-de-France" -> {val url = "https://www.regions-et-departements.fr/images/logos-regions/thumbs/logo-ile-de-france.png"
                Picasso.get().load(url).into(holder.logo);}
            "Normandie" -> {val url = "https://www.regions-et-departements.fr/images/logos-regions/thumbs/logo-normandie.png"
                Picasso.get().load(url).into(holder.logo);}
            "Nouvelle-Aquitaine" -> {val url = "https://www.regions-et-departements.fr/images/logos-regions/thumbs/logo-nouvelle-aquitaine.png"
                Picasso.get().load(url).into(holder.logo);}
            "Occitanie" -> {val url = "https://www.regions-et-departements.fr/images/logos-regions/thumbs/logo-occitanie.png"
                Picasso.get().load(url).into(holder.logo);}
            "Pays de la Loire" -> {val url = "https://www.regions-et-departements.fr/images/logos-regions/thumbs/logo-pays-de-la-loire.png"
                Picasso.get().load(url).into(holder.logo);}
            "Provence-Alpes-Côte d'Azur" -> {val url = "https://www.regions-et-departements.fr/images/logos-regions/thumbs/logo-provence-alpes-cote-d-azur.png"
                Picasso.get().load(url).into(holder.logo);}

        }

        //holder.telephone.text = musee.telephone
        //holder.departement.text = musee.departement
        //holder.url.text = musee.url
        //holder.id.text = musee.id
        holder.nom.text = musee.nom
        //holder.adresse.text = musee.adresse
        //holder.longitude.text = musee.longitude.toString()
        holder.favori.text = musee.favori.toString()
    }

    override fun getItemCount(): Int {
        return musees.size
    }

    fun refreshData(allMusees: List<Musee>) {
        this.musees = allMusees;
    }
}