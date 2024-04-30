package com.lyang25.catapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyang25.catapp.BuildConfig
import com.lyang25.catapp.R
import com.lyang25.catapp.ui.theme.CatAppTheme

@Composable
fun InfoScreen(
    navToHomeScreen: () -> Unit = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(6f)
        ) {
            Image(
                painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                Modifier
                    .clip(RoundedCornerShape(48.dp))
                    .background(Color.hsl(292.5f, 1f, 0.847f))
                    .size(100.dp),
            )

            Column(
                modifier = Modifier
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    textAlign = TextAlign.Center,
                    color = Color.hsl(314f, 1f, 0.216f),
                    fontWeight = FontWeight.Black,
                    fontSize = 56.sp,
                )
                Text(
                    text = BuildConfig.VERSION_NAME,
                    textAlign = TextAlign.Center,
                    color = Color.hsl(281.6f, 1f, 0.471f),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                )
                Text(
                    text = BuildConfig.APPLICATION_ID,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    fontSize = 11.sp,
                )
                Text(
                    text = BuildConfig.BUILD_TIME.toString(),
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Light,
                    fontSize = 11.sp,
                )
            }
        }

        Box(
            Modifier.weight(1f)
        ) {
            Button(
                onClick = { navToHomeScreen() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.hsl(314f, 1f, 0.216f),
                    contentColor = Color.White
                ),
            ) {
                Text(text = stringResource(id = R.string.back))
            }
        }

        Row(
            Modifier.weight(1f),
        ) {
            Text(
                text = stringResource(id = R.string.author),
                color = Color.DarkGray,
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfoScreenPreview() {
    CatAppTheme {
        InfoScreen()
    }
}

