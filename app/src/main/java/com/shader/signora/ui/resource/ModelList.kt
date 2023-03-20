package com.shader.signora.ui.resource

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.shader.signora.R

@Composable
fun ModelList(
    onUpdateTitle: (String) -> Unit = {},
) {
    onUpdateTitle(stringResource(R.string.nav_model))

    Text("model")
}

@Preview
@Composable
private fun ModelListPreview() {
    ModelList()
}