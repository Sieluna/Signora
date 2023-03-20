package com.shader.signora.ui.resource

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shader.signora.R
import com.shader.signora.viewmodels.ResourceViewModel

@Composable
fun Resource(
    onUpdateTitle: (String) -> Unit,
    resourceId: String?,
    resourceViewModel: ResourceViewModel = viewModel()
) {
    onUpdateTitle(stringResource(R.string.nav_resource))


}

@Preview
@Composable
private fun ResourceFragmentPreview() {
    Resource(
        onUpdateTitle = {},
        resourceId = "preview"
    )
}