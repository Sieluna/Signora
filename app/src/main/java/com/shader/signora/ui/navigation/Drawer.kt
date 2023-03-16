package com.shader.signora.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.shader.signora.NavigationConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Drawer(
    configs: Map<String, Array<NavigationConfig>>,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavController
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Column {
        DrawerHeader()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            configs["default"]?.forEach {
                if (configs[it.group] !== null) {
                    DrawerCollapsableItem(
                        config = it,
                        children = configs[it.group]!!,
                        selected = currentRoute == it.route,
                        onItemClick = { config ->
                            navController.navigate(config.route) {
                                navController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                        }
                    )
                } else {
                    DrawerItem(
                        config = it,
                        selected = currentRoute == it.route,
                        onItemClick = { config ->
                            navController.navigate(config.route) {
                                navController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun DrawerPreview() {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            Drawer(
                configs = mapOf(
                    "default" to arrayOf(NavigationConfig.Resources, NavigationConfig.Shaders),
                    "shader" to arrayOf(NavigationConfig.Resources)
                ),
                scope = scope,
                scaffoldState = scaffoldState,
                navController = navController
            )
        }
    ) {
        Box(Modifier.padding(it))
    }
}