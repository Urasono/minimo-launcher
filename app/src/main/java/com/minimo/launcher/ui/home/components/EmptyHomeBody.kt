package com.minimo.launcher.ui.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.minimo.launcher.R
import com.minimo.launcher.ui.components.EmptyScreenView

@Composable
fun EmptyHomeBody(
    paddingValues: PaddingValues,
    onAddFavouriteAppsClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .consumeWindowInsets(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        EmptyScreenView(
            title = stringResource(R.string.no_favourites_added),
            subTitle = stringResource(R.string.add_your_favourite_apps_to_access_them_easily),
            button = {
                Button(onClick = onAddFavouriteAppsClick) {
                    Text(text = stringResource(R.string.add_favourite_apps))
                }
            }
        )
    }
}
