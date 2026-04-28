package com.example.basictodolist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Parent() {
    //Input data
    var task: String by rememberSaveable { (mutableStateOf("")) }
    //list of items
    val listOfTasks = remember { (mutableStateListOf<String>()) }
    //Alert Dialog state
    var trigger by rememberSaveable { (mutableStateOf(false)) }
    //storing state of task to delete using alert dialog because state hoisting is not used
    var taskToDelete by rememberSaveable { (mutableStateOf("")) }
    //storing the state of deleted task so it can be reverted via Snack bar
    var deletedTask by rememberSaveable { (mutableStateOf("")) }
    //SnackBar state
    var showSnack by rememberSaveable { (mutableStateOf(false)) }

    //Functionality Lambda Functions
    //Add task Button
    val add = {
        listOfTasks.add(task); task = ""
    }

    //Delete task Button
    val delete = { taskItem: String ->
        trigger = true
        taskToDelete = taskItem
    }

    //For Snack Bar
    val onUndoClick = {
        listOfTasks.add(deletedTask)
        showSnack = false
    }

    //For AlertDialog confirmation and Snack Bar to appear
    val onDeleteConfirm = {
        deletedTask = taskToDelete
        listOfTasks.remove(taskToDelete)

        trigger = false
        showSnack = true
    }


    //Child Composables
    Alert(onDeleteConfirm, trigger = false)
    Snack(onUndoClick, showSnack)




    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(20.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .wrapContentHeight()
                    .width(270.dp),
                value = task,
                onValueChange = { task = it },
                placeholder = {
                    Text(
                        text = "Write here...", fontSize = 20.sp, color = Color.Gray
                    )
                })

            Spacer(Modifier.weight(1f))

            IconButton(
                onClick = { add() }, enabled = task.isNotEmpty()
            ) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    tint = Color.LightGray,
                    contentDescription = "Add task",
                    modifier = Modifier.size(45.dp)
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        if (listOfTasks.isEmpty()) {

            Text(
                text = "No tasks yet", fontSize = 20.sp, color = Color.LightGray
            )
        } else {

            LazyColumn(modifier = Modifier, userScrollEnabled = true) {

                items(listOfTasks) { taskItems ->

                    Row(
                        modifier = Modifier, verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = taskItems,
                            fontSize = 20.sp,
                            color = Color.White,
                            modifier = Modifier
                                .wrapContentHeight()
                                .width(270.dp)
                        )
                        Spacer(Modifier.weight(1f))
                        IconButton(onClick = {
                            delete(taskItems)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                tint = Color.LightGray,
                                contentDescription = "Delete task",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                    Spacer(Modifier.height(5.dp))
                    HorizontalDivider()
                    Spacer(Modifier.height(5.dp))
                }
            }
        }
    }
}







