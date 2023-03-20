package com.shader.signora.ui.resource

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.shader.signora.R

@Composable
fun TextureList(
    onUpdateTitle: (String) -> Unit = {}
) {
    onUpdateTitle(stringResource(R.string.nav_texture))

    Text("texture")
}

@Preview
@Composable
private fun TextureListPreview() {
    TextureList()
}