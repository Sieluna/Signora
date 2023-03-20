package com.shader.signora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.shader.signora.ui.App
import com.shader.signora.ui.theme.SignoraTheme
import com.shader.signora.viewmodels.MainViewModel

const val DEFAULT_KEY = "default"
const val RESOURCE_KEY = "resource"
const val SHADER_KEY = "shader"

sealed class NavigationConfig(var route: String, var group: String? = null, @DrawableRes var icon: Int = R.drawable.ic_nav_shared, @StringRes var title: Int = R.string.nav_shared) {
    object Resources: NavigationConfig("resources", RESOURCE_KEY, R.drawable.ic_nav_resource, R.string.nav_resource)
    object Textures: NavigationConfig("textures", title = R.string.nav_texture)
    object Models: NavigationConfig("models", title = R.string.nav_model)
    object Resource: NavigationConfig("$RESOURCE_KEY/{resourceId}")
    object Shaders: NavigationConfig("shaders", SHADER_KEY, R.drawable.ic_nav_shader, R.string.nav_shader)
    object Shader: NavigationConfig("$SHADER_KEY/{shaderId}")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setContent {
            SignoraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                    content = { App(mainViewModel) }
                )
            }
        }
    }
}