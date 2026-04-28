package com.example.basictodolist

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Alert(
    onDeleteConfirm: () -> Unit,
    trigger: Boolean,
    closeAlert: () -> Unit,
) {
    if (trigger) {
        AlertDialog(
            onDismissRequest = { closeAlert() },
            title = { Text(text = "Delete task") },
            text = { Text(text = "Are you sure you want to delete this task?") },
            confirmButton = {
                Button(
                    onClick = {
                        onDeleteConfirm()
                    },
                    colors = ButtonDefaults.buttonColors(Color.Gray )
                ) {
                    Text(text = "Yes", fontSize = 15.sp, color = Color.White)
                }
            },
            dismissButton = {
                Button(
                    onClick = { closeAlert() },
                    colors = ButtonDefaults.buttonColors(Color.Black.copy(alpha = 0.5f))
                ) {
                    Text(text = "No", fontSize = 15.sp, color = Color.White)
                }
            },
            shape = RoundedCornerShape(15.dp)
        )
    }
}