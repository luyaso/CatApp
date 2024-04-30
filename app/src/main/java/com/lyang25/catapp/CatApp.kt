package com.lyang25.catapp

import android.app.Application
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lyang25.catapp.ui.screens.catapi.Cat

class CatApp : Application() {

    companion object {
        private lateinit var instance: CatApp

        lateinit var tcaSample: List<Cat>

        const val DEFAULT_IMAGE_URL = "https://i.natgeofe.com/n/548467d8-c5f1-4551-9f58-6817a8d2c45e/NationalGeographic_2572187_3x2.jpg"
        const val DEFAULT_NEKO_URL = "https://api.neko-atsume.emshea.com/"

        const val FLIP_DURATION = 400
    }

    override fun onCreate() {
        instance = this
        super.onCreate()

        val gson = Gson()
        val jsonString =
            assets.open("nekoresponse.json").bufferedReader().use {
                it.readText()
            }
        tcaSample = gson.fromJson(jsonString, object : TypeToken<List<Cat>>() {}.type)
        print(tcaSample)
    }
}