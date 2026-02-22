package com.minimo.launcher.ui.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.minimo.launcher.ui.components.ScreenTimeView
import com.minimo.launcher.ui.components.TimeAndDateView
import com.minimo.launcher.ui.home.HomeScreenState
import com.minimo.launcher.ui.home.HomeViewModel
import com.minimo.launcher.ui.theme.Dimens
import com.minimo.launcher.utils.launchAppInfo
import com.minimo.launcher.utils.openDigitalWellbeing
import com.minimo.launcher.utils.uninstallApp

@Composable
fun HomeBody(
    paddingValues: PaddingValues,
    state: HomeScreenState,
    viewModel: HomeViewModel,
    homeLazyListState: LazyListState,
    nestedScrollConnection: NestedScrollConnection
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .consumeWindowInsets(paddingValues)
    ) {
        if (state.showHomeClock || state.showScreenTimeWidget) {
            Column(
                modifier = Modifier.padding(
                    horizontal = Dimens.APP_HORIZONTAL_SPACING,
                    vertical = 16.dp
                )
            ) {
                if (state.showHomeClock) {
                    TimeAndDateView(
                        horizontalAlignment = state.homeClockAlignment,
                        clockMode = state.homeClockMode,
                        twentyFourHourFormat = state.twentyFourHourFormat,
                        showBatteryLevel = state.showBatteryLevel
                    )
                }

                if (state.showScreenTimeWidget && state.screenTime.isNotEmpty()) {
                    if (state.showHomeClock) {
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    ScreenTimeView(
                        horizontalAlignment = state.homeClockAlignment,
                        screenTime = state.screenTime,
                        refreshScreenTime = viewModel::refreshScreenTime,
                        onClick = context::openDigitalWellbeing
                    )
                }
            }
        }

        LazyColumn(
            state = homeLazyListState,
            modifier = Modifier
                .weight(1f)
                .nestedScroll(nestedScrollConnection),
            contentPadding = paddingValues,
            verticalArrangement = state.appsArrangementVertical
        ) {
            items(items = state.favouriteApps, key = { it.id }) { appInfo ->
                AppNameItem(
                    modifier = Modifier.animateItem(),
                    appName = appInfo.name,
                    isFavourite = appInfo.isFavourite,
                    isHidden = appInfo.isHidden,
                    isWorkProfile = appInfo.isWorkProfile,
                    onClick = { viewModel.onLaunchAppClick(appInfo) },
                    onToggleFavouriteClick = {
                        viewModel.onToggleFavouriteAppClick(
                            appInfo
                        )
                    },
                    onRenameClick = { viewModel.onRenameAppClick(appInfo) },
                    onToggleHideClick = { viewModel.onToggleHideClick(appInfo) },
                    onAppInfoClick = { context.launchAppInfo(appInfo) },
                    appsArrangement = state.appsArrangementHorizontal,
                    textSize = state.homeTextSize.sp,
                    onUninstallClick = { context.uninstallApp(appInfo) },
                    showNotificationDot = appInfo.showNotificationDot,
                    verticalPadding = state.homeAppVerticalPadding.dp
                )
            }
        }
    }
}
