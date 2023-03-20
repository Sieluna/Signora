package com.shader.signora.ui.resource

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.shader.signora.R
import java.io.File

@Composable
fun ResourceItem() {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(File(""))
            .size(Size.ORIGINAL)
            .build()
    )

    Image(
        painter = painter,
        contentDescription = stringResource(R.string.resource_image_desc)
    )
}

@Preview
@Composable
private fun ResourceItemPreview() {
    ResourceItem()
}