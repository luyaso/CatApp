package com.lyang25.catapp.ui.screens.catscroll

import com.lyang25.catapp.CatApp
import com.lyang25.catapp.ui.screens.catapi.Cat
import com.lyang25.catapp.ui.screens.catapi.CatApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CatRepository {

    companion object {
        val catApi: CatApi by lazy {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(CatApp.DEFAULT_NEKO_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return@lazy retrofit.create(CatApi::class.java)
        }
    }

    fun fetchData(onFailure: () -> Unit, onSuccess: (List<Cat>) -> Unit) {

        val request: Call<List<Cat>> = catApi.fetchAllCats()

        request.enqueue(object : Callback<List<Cat>> {
            override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
                onFailure()
            }

            override fun onResponse(
                call: Call<List<Cat>>,
                response: Response<List<Cat>>,
            ) {
                if (response.isSuccessful) {
                    val data: List<Cat>? = response.body()
                    data?.let {
                        onSuccess(it)
                    }
                }
            }
        })
    }

}