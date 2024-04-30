package com.lyang25.catapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lyang25.catapp.ui.screens.catapi.CatViewModel
import com.lyang25.catapp.ui.screens.catscroll.CatRepository
import com.lyang25.catapp.ui.screens.catscroll.ScrollViewModel
import com.lyang25.catapp.ui.theme.CatAppTheme

class ScrollViewModelFactory(private val repository: CatRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ScrollViewModel(catRepository = repository) as T
    }
}

class MainActivity : ComponentActivity() {

    /* NOTE: changed API, ended up not needing an api_key for new API */
//    private val missingApiAlert = mutableStateOf(BuildConfig.api_key.isEmpty())

    private val catRepository = CatRepository()

    private val catViewModel: CatViewModel by viewModels()

    private val viewModelFactory = ScrollViewModelFactory(catRepository)
    private val scrollViewModel: ScrollViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val readFromFile = remember { mutableStateOf(false) }
            scrollViewModel.fetchData(fromFile = readFromFile.value)

            CatAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CatNav(
                        catViewModel = catViewModel,
                        scrollViewModel = scrollViewModel,
                        readFromFile = readFromFile,
                    ) {
                        scrollViewModel.fetchData(readFromFile.value)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    val readFromFile = remember { mutableStateOf(false) }
    CatAppTheme {
        CatNav(
            catViewModel = CatViewModel(),
            scrollViewModel = ScrollViewModel(CatRepository()),
            readFromFile = readFromFile,
        ) {}
    }
}