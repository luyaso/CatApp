package com.lyang25.catapp.ui.screens.catapi

import androidx.lifecycle.ViewModel
import com.lyang25.catapp.CatApp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CatViewModel : ViewModel() {

    companion object {
        val catApi: CatApi by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(CatApp.DEFAULT_NEKO_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return@lazy retrofit.create(CatApi::class.java)
        }
    }

    private val _uiState = MutableStateFlow(CatUiState())
    val uiState: StateFlow<CatUiState> = _uiState


    fun fetchACat(catNum: Int) {

        _uiState.value = _uiState.value.copy(
            loading = true,
        )
    
        val catRequest: Call<Cat> = catApi.fetchRandomCat(catNum)
        catRequest.enqueue(object : Callback<Cat> {

            override fun onFailure(call: Call<Cat>, t: Throwable) {
                _uiState.value = _uiState.value.copy(
                    loading = false,
                    catName = "No cat found."
                )
            }

            override fun onResponse(call: Call<Cat>, response: Response<Cat>) {
                response.body()?.let {
                    _uiState.value = _uiState.value.copy(
                        loading = false,
                        cat = it,
                        catName = it.CatName,
                        catImg = it.CatImage
                    )
                }
            }
        })
    }
}

data class CatUiState(
    val loading: Boolean = false,
    val cat: Cat = Cat(),
    val catName: String = "",
    val catImg: String = "",
    val showsDetail: Boolean = false,
)