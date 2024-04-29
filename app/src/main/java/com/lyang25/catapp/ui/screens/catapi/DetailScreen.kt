package com.lyang25.catapp.ui.screens.catapi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.lyang25.catapp.R
import com.lyang25.catapp.ui.theme.CatAppTheme

@Composable
fun DetailScreen(
    catUiState: CatUiState,
    navToApiScreen: () -> Unit = {},
    hideDetail: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = "${catUiState.cat.CatId}",
            fontWeight = FontWeight.Bold
        )

        AsyncImage(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp),
            model = catUiState.cat.MementoImage,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.shutter_reverse)
        )

        Column (
            modifier = Modifier
                .weight(3f)
                .padding(36.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "${stringResource(id = R.string.catname)}: ${catUiState.catName}",
                fontSize = 20.sp
            )

            Text(
                text = "${stringResource(id = R.string.type)}: ${catUiState.cat.CatType}",
                fontSize = 20.sp

            )

            Text(
                text = "${stringResource(id = R.string.personality)}: ${catUiState.cat.CatPersonality}",
                fontSize = 20.sp
            )

            Text(
                text = "${stringResource(id = R.string.description)}: ${catUiState.cat.CatDescription}",
                fontSize = 20.sp
            )
        }

        Box(
            modifier = Modifier
                .weight(2f)
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { hideDetail() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.hsl(314f, 1f, 0.216f),
                    contentColor = Color.White
                )
            ) {
                Text(text = stringResource(id = R.string.back))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    CatAppTheme {
        DetailScreen(
            catUiState = CatUiState(),
            hideDetail = {},
        )
    }
}