package com.shader.signora.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shader.signora.R

@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(176.dp)
            .background(
                Brush.linearGradient(
                    listOf(
                        Color(0xFF9E82F0),
                        Color(0xFF42A5F5)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.app_name),
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            fontSize = 24.sp,
            letterSpacing = 8.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun DrawerHeaderPreview() {
    DrawerHeader()
}