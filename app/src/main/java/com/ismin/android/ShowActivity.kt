package com.ismin.android

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            show_date.text = "Date d' appelation :" + bundle.getString("date")
            show_adresse.text ="Adresse : " + bundle.getString("adresse")
            show_lieu.text = "Lieu : "+ bundle.getString("lieu")
            show_telephone.text = "Telephone : " + bundle.getString("telephone")
            show_url.text = "Site web : "+ bundle.getString("url")
            show_departement.text = "Département : "+ bundle.getString("departement")
        }
        val btnBack =  findViewById<Button>(R.id.r_musee_butt_retour)
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}