package com.shader.signora

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel

const val DEFAULT_KEY = "default"
const val RESOURCE_KEY = "resource"
const val SHADER_KEY = "shader"

class MainViewModel : ViewModel() {
    var screenTitle by mutableStateOf("master")
        private set

    // no getter setter for collection
    var configs = mutableStateMapOf<String, Array<NavigationConfig>>()
        private set

    init {
        configs[DEFAULT_KEY] = arrayOf(NavigationConfig.Resources, NavigationConfig.Shaders)
    }

    fun updateScreenTitle(value: String) {
        screenTitle = value
    }

    fun addResource(value: NavigationConfig): Boolean {
        val resourceList = configs.getOrDefault(RESOURCE_KEY, arrayOf())
        return when {
            resourceList.contains(value) -> false
            else -> {
                configs[RESOURCE_KEY] = arrayOf(*resourceList, value);
                true
            }
        }
    }

    fun addShader(value: NavigationConfig): Boolean {
        val resourceList = configs.getOrDefault(SHADER_KEY, arrayOf())
        return when {
            resourceList.contains(value) -> false
            else -> {
                configs[SHADER_KEY] = arrayOf(*resourceList, value);
                true
            }
        }
    }
}