package com.lyang25.catapp.ui.screens.catscroll

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lyang25.catapp.R
import com.lyang25.catapp.ui.screens.catapi.Cat
import com.lyang25.catapp.ui.theme.CatAppTheme

@Composable
fun CatScrollScreen(
    scrollUiState: ScrollUiState,
    refresh: () -> Unit,
) {
//    Text(text = stringResource(id = R.string.scroll))

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 8.dp),
        ) {
            items(
                items = scrollUiState.cats,
                itemContent = { cat ->
                    CatItem(cat = cat)
                }
            )
        }
    }
}

@Composable
fun CatItem(cat: Cat) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .background(Color.hsl(292.5f,1f,0.847f)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier.weight(1f),
            alignment = Alignment.Center,
            model = cat.CatImage,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.shutter_reverse)
        )
        
        Column (
            modifier = Modifier
                .weight(4f)
                .fillMaxHeight()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "${cat.CatId}",
                fontWeight = FontWeight.Bold
            )
            Text(text = cat.CatName)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScrollScreenPreview() {
    CatAppTheme {
        CatScrollScreen(
            scrollUiState = ScrollUiState(
                listOf(Cat())
            ),
            refresh = {},
        )
    }
}