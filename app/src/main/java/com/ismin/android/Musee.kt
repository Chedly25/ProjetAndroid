package com.ismin.android

import java.util.*

data class Musee(val latitude : Double,
                 val date_appellation : Date,
                 val region : String,
                 val lieu : String,
                 val telephone : String,
                 val departement  : String,
                 val url : String,
                 val id : String,
                 val nom : String,
                 val adresse : String,
                 val longitude : Double,
                 var favori : Boolean): java.io.Serializable
