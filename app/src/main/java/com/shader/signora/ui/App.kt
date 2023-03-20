package com.shader.signora.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.shader.signora.NavigationConfig
import com.shader.signora.ui.navigation.Drawer
import com.shader.signora.ui.navigation.TopBar
import com.shader.signora.ui.resource.ModelList
import com.shader.signora.ui.resource.Resource
import com.shader.signora.ui.resource.ResourceList
import com.shader.signora.ui.resource.TextureList
import com.shader.signora.ui.shader.Shader
import com.shader.signora.ui.shader.ShaderList
import com.shader.signora.viewmodels.MainViewModel

@Composable
fun App(mainViewModel: MainViewModel) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(scope, scaffoldState, mainViewModel.screenTitle) },
        drawerContent = { Drawer(mainViewModel.configs, scope, scaffoldState, navController) }
    ) {
        NavHost(
            navController = navController,
            startDestination = NavigationConfig.Resources.route,
            modifier = Modifier.padding(it)
        ) {
            composable(NavigationConfig.Resources.route) {
                ResourceList(
                    onUpdateTitle = { title -> mainViewModel.updateScreenTitle(title) }
                )
            }
            composable(NavigationConfig.Textures.route) {
                TextureList(
                    onUpdateTitle = { title -> mainViewModel.updateScreenTitle(title) }
                )
            }
            composable(NavigationConfig.Models.route) {
                ModelList(
                    onUpdateTitle = { title -> mainViewModel.updateScreenTitle(title) }
                )
            }
            composable(NavigationConfig.Shaders.route) {
                ShaderList(
                    onUpdateTitle = { title -> mainViewModel.updateScreenTitle(title) }
                )
            }
            composable(
                NavigationConfig.Resource.route,
                listOf(navArgument("resourceId") {
                    type = NavType.StringType
                })
            ) { backStackEntry ->
                Resource(
                    onUpdateTitle = { title -> mainViewModel.updateScreenTitle(title) },
                    resourceId = backStackEntry.arguments?.getString("resourceId")
                )
            }
            composable(
                NavigationConfig.Shader.route,
                listOf(navArgument("shaderId") {
                    type = NavType.StringType
                })
            ) { backStackEntry ->
                Shader(
                    onUpdateTitle = { title -> mainViewModel.updateScreenTitle(title) },
                    shaderId = backStackEntry.arguments?.getString("shaderId")
                )
            }
        }
    }
}

@Preview
@Composable
private fun AppPreview() {
    App(viewModel())
}