package com.example.basictodolist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun Parent(
    innerPadding: PaddingValues,
    showTextField: Boolean,
    showAddButton: Boolean,
    fabClick: () -> Unit,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,

    ) {
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
    //LazyColumn state
    val listState = rememberLazyListState()
    //Async operation of lazy list to auto scroll to the most recent added task
    val scope = rememberCoroutineScope()


    //Functionality Lambda Functions
    //Add task Button
    val add = {
        if (task.isNotEmpty()) {
            listOfTasks.add(task)
            task = ""
            scope.launch {
                //scroll to the position of task in arraylist as index starts from 0,1,2,3 so we write size-1
                listState.animateScrollToItem(listOfTasks.size-1)
            }
        }
    }

    //Delete task Button
    val delete = { taskItem: String ->
        trigger = true
        taskToDelete = taskItem
    }


    //For AlertDialog confirmation and Snack Bar to appear
    val onDeleteConfirm = {
        deletedTask = taskToDelete
        listOfTasks.remove(taskToDelete)

        trigger = false

        scope.launch {
            val result = snackbarHostState.showSnackbar(
                message = "Task deleted",
                actionLabel = "Undo",
                duration = SnackbarDuration.Long
            )
            when (result) {
                SnackbarResult.Dismissed -> {

                }

                SnackbarResult.ActionPerformed -> {
                    listOfTasks.add(deletedTask)
                }
            }
        }
    }

    //Close Alert Dialog
    val closeAlert = {
        trigger = false
    }


    //Child Composables
    Alert(onDeleteConfirm, trigger, closeAlert, scope, snackbarHostState)
    //Snackbar(onUndoClick, snackbarHostState,scope)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .background(color = Color.Black)
            .padding(innerPadding)
            .padding(horizontal = 15.dp)
    ) {

        if (listOfTasks.isEmpty()) {

            Text(
                text = "No tasks yet",
                fontSize = 15.sp,
                color = Color.LightGray,
                modifier = Modifier.weight(1f)
            )
        } else {

            LazyColumn(
                modifier = Modifier.weight(1f),
                userScrollEnabled = true,
                state = listState
            ) {

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

        if (showTextField && showAddButton) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = task,
                    onValueChange = { task = it },
                    enabled = true,
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Enter task...", color = Color.LightGray) },
                    colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.Gray
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = add,
                    enabled = true

                ) {
                    Icon(
                        imageVector = Icons.Default.AddCircle,
                        tint = Color.White,
                        contentDescription = "Add",
                        modifier = Modifier.size(40.dp)

                    )
                }
            }
        }
    }
}







