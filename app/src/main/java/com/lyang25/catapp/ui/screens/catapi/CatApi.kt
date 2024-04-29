package com.lyang25.catapp.ui.screens.catapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CatApi {

    @GET("cats/{catNum}")
    fun fetchRandomCat(
        @Path("catNum") catNum: Int,
    ): Call<Cat>

}