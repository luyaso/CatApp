package com.lyang25.catapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.lyang25.catapp.ui.screens.HomeScreen
import com.lyang25.catapp.ui.screens.InfoScreen
import com.lyang25.catapp.ui.screens.SettingScreen
import com.lyang25.catapp.ui.screens.WebViewScreen
import com.lyang25.catapp.ui.screens.catapi.CatViewModel
import com.lyang25.catapp.ui.screens.catapi.DetailScreen
import com.lyang25.catapp.ui.screens.catapi.MainScreen

object Route {
    const val HOME = "Home"
    const val WEB = "Web"
    const val SETTINGS = "Settings"
    const val INFO = "Info"
    const val CATAPI = "CatAPI"
    const val CATDETAIL = "CatDetail"
}

data class Destination(
    val route: String,
    val textId: Int,
    val icon: Int,
)

val DESTINATIONS = listOf(
    Destination(
        route = Route.HOME,
        textId = R.string.home,
        icon = R.drawable.home
    ),
    Destination(
        route = Route.WEB,
        textId = R.string.webview,
        icon = R.drawable.webview
    ),
    Destination(
        route = Route.CATAPI,
        textId = R.string.catapi,
        icon = R.drawable.catpaw
    ),
    Destination(
        route = Route.SETTINGS,
        textId = R.string.settings,
        icon = R.drawable.settings
    ),
    Destination(
        route = Route.INFO,
        textId = R.string.info,
        icon = R.drawable.info
    ),
    Destination(
        route = Route.CATDETAIL,
        textId = R.string.catdetail,
        icon = R.drawable.star
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatNav(
    catViewModel: CatViewModel,
    //need more vms?
) {

    val destination = remember { mutableStateOf(Route.HOME) } // in real life should be HOME

    val catUiState by catViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.hsl(292.5f,1f,0.847f),
//                    MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = Color.hsl(314f, 1f, 0.216f),
                ),
                title = { Text(destination.value) }
            )
        },

        bottomBar = {
            NavigationBar(
                modifier = Modifier.fillMaxWidth()
            ) {

                DESTINATIONS.subList(0,3).forEach { dest ->
                    NavigationBarItem(
                        label = { Text(text = stringResource(id = dest.textId)) },
                        selected = destination.value == dest.route,
                        onClick = { destination.value = dest.route },
                        icon = {
                            Icon(
                                painterResource(id = dest.icon),
                                contentDescription = stringResource(id = dest.textId)
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier.padding(innerPadding),
        ) {

            when (destination.value) {
                Route.HOME -> {
                    HomeScreen(
                        navToSettingScreen = { destination.value = Route.SETTINGS },
                        navToInfoScreen = { destination.value = Route.INFO }
                    )
                }

                Route.WEB -> {
                    WebViewScreen()
                }

                Route.INFO -> {
                    InfoScreen(
                        navToHomeScreen = { destination.value = Route.HOME }
                    )
                }

                Route.SETTINGS -> {
                    SettingScreen(
                        navToHomeScreen = { destination.value = Route.HOME }
                    )
                }
                
                Route.CATAPI -> {
                    MainScreen(
                        catUiState = catUiState,
                        onGo = {
                            catViewModel.fetchACat(it)
                        },
                        showDetail = {
                            destination.value = Route.CATDETAIL
                        },
                        hideDetail = {}
                    )
                }

                Route.CATDETAIL -> {
                    DetailScreen(
                        catUiState,
                        navToApiScreen = { destination.value = Route.CATAPI },
                        hideDetail = { destination.value = Route.CATAPI }
                    )
                }
            }
        }
    }
}


