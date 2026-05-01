package com.example.basictodolist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Screen() {
    //Show TextField
    var showTextField by rememberSaveable { (mutableStateOf(false)) }
    //Show add task button
    var showAddButton by rememberSaveable { (mutableStateOf(false)) }
    //SnackBar state
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }


    //Show TextField after pressing FAB
    val fabClick = {
        showTextField = true
        showAddButton = true
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = { AppTopBar() },
        bottomBar = { AppBottomBar() },
//        snackbarHost = {
//            SnackbarHost(
//                snackbarHostState,
//                modifier = Modifier. androidx . compose . ui .padding(bottom = if (showTextField) 80.dp else 0.dp)
////                    .navigationBarsPadding()
////                    .imePadding()
//            )
//        },

        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                androidx.compose.material3.Snackbar(
                    modifier = Modifier.padding(bottom = 80.dp),
                    snackbarData = data
                )
            }
        },

        floatingActionButton = {
            if (!showTextField && !showAddButton) {
                FAB(fabClick)
            }
        }

    ) { innerPadding ->
        Parent(innerPadding, showTextField, showAddButton, fabClick, snackbarHostState, scope)
    }
}