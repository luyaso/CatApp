package com.lyang25.catapp.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lyang25.catapp.R
import com.lyang25.catapp.ui.screens.catapi.CatUiState
import com.lyang25.catapp.ui.screens.catapi.CatViewModel
import com.lyang25.catapp.ui.theme.CatAppTheme

@Composable
fun SettingScreen(
    catViewModel: CatViewModel,
    catUiState: CatUiState,
    navToHomeScreen: () -> Unit = {},
    readFromFile: MutableState<Boolean>,
) {

    var checked by remember { mutableStateOf(catUiState.showMemento) }
    var answerIdx by remember { mutableIntStateOf(1) }

    Column() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(4f),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(text = stringResource(id = R.string.switchmemento))

                Spacer(
                    modifier = Modifier.size(12.dp)
                )

                Switch(
                    checked = checked,
                    onCheckedChange = {
                        catViewModel.switchMemento(it)
                        checked = catUiState.showMemento
                    },
                    colors = SwitchDefaults.colors(
                        checkedTrackColor = Color.hsl(281.6f, 1f, 0.471f),
                    )
                )
            }

            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(text = "Radio buttons here")

                SettingsRadio(
                    catUiState = catUiState,
                    stuff = listOf(0, 1)
                )
            }

            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(text = stringResource(id = R.string.readfromfile))

                Spacer(
                    modifier = Modifier.size(12.dp)
                )

                Switch(
                    checked = readFromFile.value,
                    onCheckedChange = { isChecked ->
                        readFromFile.value = isChecked
                    },
                    colors = SwitchDefaults.colors(
                        checkedTrackColor = Color.hsl(281.6f, 1f, 0.471f),
                    )
                )
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { navToHomeScreen() },
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
fun SettingScreenPreview() {
    val readFromFile = remember { mutableStateOf(false) }
    CatAppTheme {
        SettingScreen(
            catViewModel = CatViewModel(),
            catUiState = CatUiState(),
            readFromFile = readFromFile
        )
    }
}


