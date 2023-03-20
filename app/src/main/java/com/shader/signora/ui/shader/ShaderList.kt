package com.shader.signora.ui.shader

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shader.signora.R
import com.shader.signora.viewmodels.ShaderViewModel

@Composable
fun ShaderList(
    onUpdateTitle: (String) -> Unit = {},
    shaderViewModel: ShaderViewModel = viewModel()
) {
    onUpdateTitle(stringResource(R.string.nav_resource))

    Text(text = "shader fragment")
}

@Preview
@Composable
private fun ShaderListPreview() {
    ShaderList()
}