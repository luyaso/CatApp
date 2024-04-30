package com.lyang25.catapp.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.lyang25.catapp.R

@Composable
fun SettingsDialog(
    readFromFile: MutableState<Boolean>,
    onDone: () -> Unit,
) {
    Dialog(onDismissRequest = { }) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
        ) {
            Column(
                Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row {
                    Text(
                        modifier = Modifier
                            .weight(3f)
                            .fillMaxWidth()
                            .padding(16.dp),
                        text = stringResource(R.string.readfromfile),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Switch(
                        modifier = Modifier
                            .weight(1f),
                        checked = readFromFile.value,
                        onCheckedChange = { isChecked ->
                            readFromFile.value = isChecked
                        })
                }

                Spacer(Modifier.size(48.dp))

                Row {
                    Button(
                        onClick = { onDone() },
                    ) { Text(text = stringResource(R.string.done)) }
                }
            }
        }
    }
}