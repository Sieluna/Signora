package com.shader.signora.ui.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.shader.signora.NavigationConfig
import com.shader.signora.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerItem(
    config: NavigationConfig,
    selected: Boolean,
    onItemClick: (NavigationConfig) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(config) }
            .height(64.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp + ButtonDefaults.IconSpacing.times(2), 4.dp)
                .clip(MaterialTheme.shapes.medium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            leadingIcon?.let { it() } ?: Icon(
                painter = painterResource(config.icon),
                contentDescription = stringResource(config.title),
                modifier = Modifier.fillMaxHeight(0.9f),
                tint = if (selected) MaterialTheme.colors.primary else Color.Black
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(
                text = stringResource(config.title),
                color = if (selected) MaterialTheme.colors.primary else Color.Black
            )
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterEnd
            ) {
                trailingIcon?.let { it() }
            }
        }
    }
}

@Composable
fun DrawerCollapsableItem(
    config: NavigationConfig,
    children: Array<NavigationConfig>,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavController
) {
    var collapsed: Boolean by remember { mutableStateOf(false) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    DrawerItem(config,
        selected = children.any { currentRoute == it.route } || currentRoute == config.route,
        onItemClick = {
            navController.navigate(it.route) {
                navController.graph.startDestinationRoute?.let { route ->
                    popUpTo(route) {
                        saveState = true
                    }
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        trailingIcon = {
            Icon(
                modifier = Modifier.clickable { collapsed = !collapsed },
                imageVector = Icons.Default.run { if (collapsed) KeyboardArrowLeft else KeyboardArrowDown },
                contentDescription = "Arrow"
            )
        }
    )

    if (!collapsed) {
        Divider()
        children.forEach {
            DrawerItem(it,
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
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_nav_shared),
                        contentDescription = "Dot"
                    )
                }
            )
            Divider()
        }
    }
}

@Preview
@Composable
private fun DrawerItemPreview() {
    Column {
        DrawerItem(
            config = NavigationConfig.Resources,
            selected = true,
            onItemClick = {}
        )
        DrawerItem(
            config = NavigationConfig.Resources,
            selected = false,
            onItemClick = {}
        )
    }
}

@Preview
@Composable
private fun DrawerCollapsableItemPreview() {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    Column {
        DrawerCollapsableItem(
            config = NavigationConfig.Resources,
            children = arrayOf(NavigationConfig.Shaders),
            scope = scope,
            scaffoldState = scaffoldState,
            navController = navController
        )
        DrawerCollapsableItem(
            config = NavigationConfig.Resources,
            children = arrayOf(NavigationConfig.Shaders),
            scope = scope,
            scaffoldState = scaffoldState,
            navController = navController
        )
    }
}