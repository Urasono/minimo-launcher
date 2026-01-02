package com.minimo.launcher.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.minimo.launcher.R
import com.minimo.launcher.ui.theme.Dimens

@Composable
fun MinimoSettingsItem(
    modifier: Modifier,
    horizontalArrangement: Arrangement.Horizontal,
    textSize: TextUnit,
    onClick: () -> Unit,
    verticalPadding: Dp = 16.dp,
) {
    val lineHeight by remember { derivedStateOf { textSize * 1.2 } }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(
                PaddingValues(
                    horizontal = Dimens.APP_HORIZONTAL_SPACING,
                    vertical = verticalPadding
                )
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontalArrangement
    ) {
        Text(
            text = stringResource(R.string.minimo_settings),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = textSize,
            lineHeight = lineHeight,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}