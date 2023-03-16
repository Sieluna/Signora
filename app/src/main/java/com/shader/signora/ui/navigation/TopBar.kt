package com.shader.signora.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBar(scope: CoroutineScope, scaffoldState: ScaffoldState, title: String) {
    var expanded: Boolean by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(title, fontSize = 18.sp)
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
               }
            ) {
                Icon(Icons.Filled.Menu, "Menu")
            }
        },
        actions = {
            IconButton(
                onClick = { expanded = !expanded }
            ) {
                Icon(Icons.Filled.MoreVert, "More")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(onClick = {}) {
                    Text("Settings")
                }
            }
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary
    )
}

@Preview
@Composable
private fun TopBarPreview() {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(scope, scaffoldState, "Preview") },
    ) {
        Box(Modifier.padding(it))
    }
}