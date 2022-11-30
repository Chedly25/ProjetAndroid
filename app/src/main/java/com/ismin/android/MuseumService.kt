package com.ismin.android

import retrofit2.Call
import retrofit2.http.GET

interface MuseumService {
    @GET("/musees")
    fun getMuseums(): Call<List<Musee>>
}