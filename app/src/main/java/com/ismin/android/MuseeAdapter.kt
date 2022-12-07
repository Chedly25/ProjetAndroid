package com.ismin.android

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ismin.android.R.drawable
import com.squareup.picasso.Picasso
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList

class MuseeAdapter(private var musees: List<Musee>, private val activity: MainActivity) : RecyclerView.Adapter<MuseumViewHolder>(),
    Filterable {

    private lateinit var  mListener : onItemClickListener
    private val searchList = musees
    private  var  mainList : ArrayList<Musee> = arrayListOf()


    interface onItemClickListener{
        fun onItemClick(position :Int)
    }


    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener=listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MuseumViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(
            R.layout.row_musee, parent,
            false
        )
        return MuseumViewHolder(row, mListener,)
    }

    override fun onBindViewHolder(holder: MuseumViewHolder, position: Int) {
        val musee = musees[position]
        holder.region.text = musee.region
        holder.lieu.text = musee.lieu

        when (holder.region.text) {
            "Auvergne-Rhône-Alpes" -> {val url = "https://www.regions-et-departements.fr/images/logos-regions/thumbs/logo-auvergne-rhone-alpes.png"
                Picasso.get().load(url).into(holder.logo);

            }
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

        holder.favori.text=musee.favori.toString()
        when(holder.favori.text){
            "true"->{
                val favoriteIcon = ContextCompat.getDrawable(
                    holder.btnFavori.context,
                    drawable.ic_baseline_star_24
                )
                holder.btnFavori.setImageDrawable(favoriteIcon)
            }
            "false"->{
                val favoriteIcon = ContextCompat.getDrawable(
                    holder.btnFavori.context,
                    drawable.ic_baseline_star_border_24

                )
                holder.btnFavori.setImageDrawable(favoriteIcon)
            }

        }

        holder.btnFavori.setOnClickListener {
            musees[position].favori = !musees[position].favori
            if(musees[position].favori) {
                val favoriteIcon = ContextCompat.getDrawable(
                    holder.btnFavori.context,
                    drawable.ic_baseline_star_24
                )
                activity.updateFavoris(musees[position].nom)
                activity.setFavoris(musees[position])
                holder.btnFavori.setImageDrawable(favoriteIcon)
            }
            else {
                val favoriteIcon = ContextCompat.getDrawable(
                    holder.btnFavori.context,
                    drawable.ic_baseline_star_border_24

                )

                activity.updateFavoris(musees[position].nom)
                activity.removeFavoris(musees[position])
                holder.btnFavori.setImageDrawable(favoriteIcon)
            }
        }
        holder.nom.text = musee.nom
    }



    override fun getItemCount(): Int {
        return musees.size
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = ArrayList<Musee>()
                if(constraint!!.isBlank() or constraint!!.isEmpty()){
                    filteredList.addAll(searchList)
                }else{
                    val filterPattern = constraint.toString().toLowerCase(Locale.getDefault()).trim()
                    searchList.forEach {
                        if(it.nom.toLowerCase().contains(filterPattern) ||it.region.toLowerCase().contains(filterPattern)||it.departement.toLowerCase().contains(filterPattern)){
                            filteredList.add(it)
                        }
                    }
                }
                val result = FilterResults()
                result.values = filteredList
                return result
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                mainList.clear()
                mainList.addAll(results!!.values as ArrayList<Musee>)
                notifyDataSetChanged()
            }
        }
    }

    fun getMainList():ArrayList<Musee>{
        return mainList
    }
}