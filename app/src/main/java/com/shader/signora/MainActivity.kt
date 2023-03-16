package com.shader.signora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shader.signora.ui.navigation.Drawer
import com.shader.signora.ui.navigation.TopBar
import com.shader.signora.ui.resource.ResourceFragment
import com.shader.signora.ui.shader.ShaderFragment
import com.shader.signora.ui.theme.SignoraTheme

sealed class NavigationConfig(var route: String, var group: String? = null, @DrawableRes var icon: Int = R.drawable.ic_nav_shared, @StringRes var title: Int = R.string.nav_shared) {
    object Resources: NavigationConfig("resources", RESOURCE_KEY, R.drawable.ic_nav_resource, R.string.nav_resource)
    object Resource: NavigationConfig("resource/{resourceId}")
    object Shaders: NavigationConfig("shaders", SHADER_KEY, R.drawable.ic_nav_shader, R.string.nav_shader)
    object Shader: NavigationConfig("shader/{shaderId}")
}

@Composable
fun MainActivityLayout(mainViewModel: MainViewModel = viewModel()) {
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
            startDestination = NavigationConfig.Shaders.route,
            modifier = Modifier.padding(it)
        ) {
            composable(NavigationConfig.Resources.route) { ResourceFragment(mainViewModel) }
            composable(NavigationConfig.Resource.route) { }
            composable(NavigationConfig.Shaders.route) { ShaderFragment(mainViewModel) }
            composable(NavigationConfig.Shader.route) { }
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignoraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                    content = { MainActivityLayout() }
                )
            }
        }
    }
}