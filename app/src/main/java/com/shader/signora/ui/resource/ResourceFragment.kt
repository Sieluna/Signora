package com.shader.signora.ui.resource

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.shader.signora.MainViewModel
import com.shader.signora.R

@Composable
fun ResourceFragment(mainViewModel: MainViewModel) {
    mainViewModel.updateScreenTitle(stringResource(R.string.nav_resource))

    Text(text = "res fragment")
}