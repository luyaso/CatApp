package com.lyang25.catapp.ui.screens.catscroll

import androidx.lifecycle.ViewModel
import com.lyang25.catapp.CatApp
import com.lyang25.catapp.ui.screens.catapi.Cat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ScrollViewModel(private val catRepository: CatRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(ScrollUiState())
    val uiState: StateFlow<ScrollUiState> = _uiState

    private fun updateState(catResponse: List<Cat>) {
        _uiState.value = _uiState.value.copy(
            cats = catResponse
        )
    }

    fun fetchData(fromFile: Boolean) {
        if (fromFile) {
            val data = CatApp.tcaSample
            updateState(data)
        } else {
            catRepository.fetchData(onFailure = {
                // Todo: add error handling
            }) { catResponse ->
                updateState(catResponse)
            }
        }
    }
}

data class ScrollUiState(
    val cats: List<Cat> = listOf()
)