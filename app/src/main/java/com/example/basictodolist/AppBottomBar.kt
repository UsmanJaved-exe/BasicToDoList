package com.example.basictodolist

import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppBottomBar() {
    BottomAppBar(containerColor = Color.Black) { }
}