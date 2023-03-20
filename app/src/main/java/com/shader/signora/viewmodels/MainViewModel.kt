package com.shader.signora.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.shader.signora.DEFAULT_KEY
import com.shader.signora.NavigationConfig
import com.shader.signora.RESOURCE_KEY
import com.shader.signora.SHADER_KEY
import com.shader.signora.data.AppDatabase

class MainViewModel(app: Application) : AndroidViewModel(app) {
    var screenTitle by mutableStateOf("master")
        private set

    var configs = mutableStateMapOf<String, Array<NavigationConfig>>()
        private set

    init {
        configs[DEFAULT_KEY] = arrayOf(NavigationConfig.Resources, NavigationConfig.Shaders)
        configs[RESOURCE_KEY] = arrayOf(NavigationConfig.Textures, NavigationConfig.Models)
        AppDatabase.getInstance(app)
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