package com.lyang25.catapp.ui.screens.catapi

import com.lyang25.catapp.CatApp

class Cat {
    var CatId: Int = 0
    var CatName: String = ""
    var CatDescription: String = ""
    var CatPersonality: String = ""
    var CatType: String = ""
    var CatImage: String = CatApp.DEFAULT_IMAGE_URL
    var MementoImage: String = ""
}

class CatResponse {
    val cat: Cat? = null
}