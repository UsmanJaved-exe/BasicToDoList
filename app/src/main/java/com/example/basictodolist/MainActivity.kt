package com.example.basictodolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.basictodolist.ui.theme.BasicToDoListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicToDoListTheme {
               Screen()
                }
            }
        }
    }


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Display() {
    Screen()
}