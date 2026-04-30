package com.example.basictodolist

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FAB(

    fabClick: () -> Unit,

    ) {
    FloatingActionButton(
        onClick = {
            fabClick()

        },
        shape = CircleShape,
        containerColor = Color.White,
        elevation = FloatingActionButtonDefaults.elevation(20.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add",
            tint = Color.Black,
            modifier = Modifier.size(30.dp)
        )
    }
}