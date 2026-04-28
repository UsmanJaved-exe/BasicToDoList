package com.example.basictodolist

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Snack(
    onUndoClick: () -> Unit,
    showSnack: Boolean,
    ) {
    if (showSnack) {
        Snackbar(
            modifier = Modifier.padding(4.dp),
            action = {
                TextButton(onClick = {
                    onUndoClick()
                }) {
                    Text(text = "UNDO", fontSize = 15.sp)
                }
            }
        ) {
            Text(text = "Task Deleted", fontSize = 15.sp)
        }
    }
}
