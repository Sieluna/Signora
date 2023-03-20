package com.shader.signora.ui.resource

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shader.signora.R
import com.shader.signora.viewmodels.ResourceViewModel

@Composable
fun ResourceList(
    onUpdateTitle: (String) -> Unit = {},
    resourceViewModel: ResourceViewModel = viewModel()
) {
    onUpdateTitle(stringResource(R.string.nav_resource))

    LazyVerticalGrid(columns = GridCells.Adaptive(128.dp)) {

    }

    Text(text = "res fragment")
}

@Preview
@Composable
private fun ResourcesFragmentPreview() {
    ResourceList()
}