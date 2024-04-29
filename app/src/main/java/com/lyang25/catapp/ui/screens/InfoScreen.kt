package com.lyang25.catapp.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.lyang25.catapp.R

@Composable
fun InfoScreen(
    navToHomeScreen: () -> Unit = {},
) {
    Text(text = stringResource(id = R.string.info))
}