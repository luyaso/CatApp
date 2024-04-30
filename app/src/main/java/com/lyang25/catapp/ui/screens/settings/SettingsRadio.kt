package com.lyang25.catapp.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.lyang25.catapp.ui.screens.catapi.CatUiState
import com.lyang25.catapp.ui.theme.CatAppTheme

@Composable
fun SettingsRadio (
    catUiState: CatUiState,
    stuff: List<Int>,
    onAnswer: (Int) -> Unit = {},
) {

    var selectedAnswer by remember { mutableStateOf(0) }

    Column {
        stuff.forEachIndexed { i, answer ->

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                RadioButton(
                    selected = selectedAnswer == answer,
                    onClick = {
                        selectedAnswer = answer
                        onAnswer(i)
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color.hsl(314f, 1f, 0.216f),
                    )
                )
                Text(
                    text = "$answer",
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsRadioPreview() {
    CatAppTheme {
        SettingsRadio (
            catUiState = CatUiState(),
            stuff = listOf(3,4,5),
        )
    }
}