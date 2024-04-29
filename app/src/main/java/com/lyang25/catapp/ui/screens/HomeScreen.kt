package com.lyang25.catapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyang25.catapp.R
import com.lyang25.catapp.ui.theme.CatAppTheme

@Composable
fun HomeScreen(
    navToInfoScreen: () -> Unit = {},
    navToSettingScreen: () -> Unit = {},
) {
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.weight(3f),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.cathome),
                contentDescription = null
            )
        }

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.kittycat),
                fontSize = 36.sp
            )
        }

        Column (
            modifier = Modifier.weight(3f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(36.dp))

            Button(
                onClick = { navToSettingScreen() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.hsl(314f, 1f, 0.216f),
                    contentColor = Color.White
                )
            ) {
                Text(text = stringResource(id = R.string.settings))
            }

            Spacer(modifier = Modifier.size(36.dp))

            Button(
                onClick = { navToInfoScreen() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.hsl(314f, 1f, 0.216f),
                    contentColor = Color.White
                )
            ) {
                Text(text = stringResource(id = R.string.info))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    CatAppTheme {
        HomeScreen(
//            catViewModel = CatViewModel()
        )
    }
}