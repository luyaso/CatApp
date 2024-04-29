package com.lyang25.catapp

import android.app.Application

class CatApp : Application() {

    companion object {
        private lateinit var instance: CatApp

        const val DEFAULT_IMAGE_URL = "https://i.natgeofe.com/n/548467d8-c5f1-4551-9f58-6817a8d2c45e/NationalGeographic_2572187_3x2.jpg"
//        const val DEFAULT_CAT_API_URL = "https://api.thecatapi.com/"
        const val DEFAULT_NEKO_URL = "https://api.neko-atsume.emshea.com/"
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}