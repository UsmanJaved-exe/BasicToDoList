package com.example.basictodolist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Screen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { AppTopBar() },
        bottomBar = { AppBottomBar() },
        snackbarHost = {},
        floatingActionButton = { FAB() },

        ) { innerPadding ->
        Parent(innerPadding)
    }
}