package com.shader.signora.ui.shader

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.shader.signora.MainViewModel
import com.shader.signora.R

@Composable
fun ShaderFragment(mainViewModel: MainViewModel) {
    mainViewModel.updateScreenTitle(stringResource(R.string.nav_shader))

    Text(text = "shader fragment")

}