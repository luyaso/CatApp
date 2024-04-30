package com.lyang25.catapp.ui.screens.catapi

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lyang25.catapp.CatApp
import com.lyang25.catapp.R
import com.lyang25.catapp.ui.theme.CatAppTheme
import kotlin.random.Random

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    catUiState: CatUiState,
    onGo: (Int) -> Unit,
    showDetail: () -> Unit,
    hideDetail: () -> Unit,
) {

    var isEnabled by remember { mutableStateOf(false) }
    var selectedCat by remember { mutableStateOf("") }
    var address by remember { mutableStateOf(CatApp.DEFAULT_IMAGE_URL) }

    if (selectedCat.isNotEmpty() && catUiState.showsDetail) {
        DetailScreen(
            catUiState = catUiState,
            hideDetail = hideDetail,
        )
    } else {
        catUiState.catImg.let { newAddress ->
            address = newAddress
        }
        catUiState.catName.let { newName ->
            selectedCat = newName
        }
        if (catUiState.cat.CatId != 0) {
            isEnabled = true
        }

        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 48.dp)
                    .weight(7f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                AsyncImage(
                    modifier = Modifier
                        .weight(4f)
                        .clickable(enabled = isEnabled) {
                            showDetail()
                        },
                    model = address,
                    contentDescription = null,
                    placeholder = painterResource(id = R.drawable.shutter_reverse)
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = selectedCat)
                }
            }

            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .weight(2f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxSize()
                        .padding(start = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = stringResource(id = R.string.select_cat))
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            onGo(Random.nextInt(1,67))
                            address = catUiState.catImg
                            selectedCat = catUiState.catName
                            isEnabled = true
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.hsl(314f, 1f, 0.216f),
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = stringResource(id = R.string.go))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    CatAppTheme {
        MainScreen(
            catUiState = CatUiState(),
            onGo = {},
            showDetail = {},
            hideDetail = {}
        )
    }
}