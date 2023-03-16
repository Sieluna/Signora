package com.shader.signora.ui.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shader.signora.NavigationConfig
import com.shader.signora.R

@Composable
fun DrawerItem(
    config: NavigationConfig,
    selected: Boolean,
    onItemClick: (NavigationConfig) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(config) }
            .height(64.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp + ButtonDefaults.IconSpacing.times(2), 4.dp)
                .clip(MaterialTheme.shapes.medium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            leadingIcon?.let { it() } ?: Icon(
                painter = painterResource(config.icon),
                contentDescription = stringResource(config.title),
                modifier = Modifier.fillMaxHeight(0.9f),
                tint = if (selected) MaterialTheme.colors.primary else Color.Black
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(
                text = stringResource(config.title),
                color = if (selected) MaterialTheme.colors.primary else Color.Black
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                trailingIcon?.let { it() }
            }
        }
    }
}

@Composable
fun DrawerCollapsableItem(
    config: NavigationConfig,
    children: Array<NavigationConfig>,
    selected: Boolean,
    onItemClick: (NavigationConfig) -> Unit
) {
    var collapsed: Boolean by remember { mutableStateOf(true) }

    DrawerItem(config, selected,
        onItemClick = {
            collapsed = !collapsed
            onItemClick(it)
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.run { if (collapsed) KeyboardArrowLeft else KeyboardArrowDown },
                contentDescription = "Arrow"
            )
        }
    )

    if (!collapsed) {
        Divider()
        children.forEach {
            DrawerItem(it, selected, onItemClick,
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_nav_shared),
                        contentDescription = "Dot"
                    )
                }
            )
            Divider()
        }
    }
}

@Preview
@Composable
private fun DrawerItemPreview() {
    Column {
        DrawerItem(
            config = NavigationConfig.Resources,
            selected = true,
            onItemClick = {}
        )
        DrawerItem(
            config = NavigationConfig.Resources,
            selected = false,
            onItemClick = {}
        )
    }
}

@Preview
@Composable
private fun DrawerCollapsableItemPreview() {
    Column {
        DrawerCollapsableItem(
            config = NavigationConfig.Resources,
            children = arrayOf(NavigationConfig.Shaders),
            selected = true,
            onItemClick = {}
        )
        DrawerCollapsableItem(
            config = NavigationConfig.Resources,
            children = arrayOf(NavigationConfig.Shaders),
            selected = false,
            onItemClick = {}
        )
    }
}