package com.ismin.android

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface MuseumService {
    @GET("/musees")
    fun getMuseums(): Call<List<Musee>>

    @PUT("api/musees/{nom}")
    fun updateFavori(@Path("nom") nom: String): Call<Musee>
}