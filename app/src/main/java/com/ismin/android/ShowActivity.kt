package com.ismin.android

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView

class ShowActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        val show_nom : TextView = findViewById(R.id.r_musee_txv_nom)
        val show_lieu : TextView = findViewById(R.id.r_musee_txv_lieu)
        val show_date : TextView = findViewById(R.id.r_musee_txv_date)
        val show_departement : TextView = findViewById(R.id.r_musee_txv_departement)
        val show_adresse : TextView = findViewById(R.id.r_musee_txv_adresse)
        val show_url : TextView = findViewById(R.id.r_musee_txv_url)
        val show_telephone : TextView = findViewById(R.id.r_musee_txv_telephone)

        val bundle : Bundle? =  intent.extras
        if (bundle != null) {
            show_nom.text = "Nom du musée : " + "\n" + bundle.getString("nom")

            if(bundle.getString("date")!=null){
                show_date.text = "Date d' appelation :" + bundle.getString("date")
            }else{
                show_date.text = "Date d' appelation :" + "pas certifié musée de France"
            }
            if(bundle.getString("adresse")!=null){
                show_adresse.text = "Adresse : " + bundle.getString("adresse")
            }else{
                show_adresse.text = "Adresse : " + "pas  disponible"
            }
            if(bundle.getString("lieu")!=null){
                show_lieu.text = "Lieu : "+ bundle.getString("lieu")
            }else{
                show_lieu.text = "Lieu : "+ "non disponible"
            }
            if(bundle.getString("telephone")!=null){
                show_telephone.text = "Telephone : " + bundle.getString("telephone")
            }else{
                show_telephone.text = "Telephone : " + "non disponible"
            }
            if(bundle.getString("url")!=null){
                show_url.text = "Site web : "+ bundle.getString("url")
            }else{
                show_url.text = "Site web : " + "non disponible"
            }
            if(bundle.getString("departement")!=null){
                show_departement.text = "Département : "+ bundle.getString("departement")
            }else{
                show_departement.text = "Département : "+ "non disponible"
            }

        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.retourne -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}