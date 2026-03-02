package com.sweak.qralarm.features.time_saved.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sweak.qralarm.features.time_saved.TimeSavedScreen

const val TIME_SAVED_SCREEN_ROUTE = "timeSavedScreen"

fun NavController.navigateToTimeSaved() = navigate(TIME_SAVED_SCREEN_ROUTE)

fun NavGraphBuilder.timeSavedScreen(
    onBackClicked: () -> Unit
) {
    composable(route = TIME_SAVED_SCREEN_ROUTE) {
        TimeSavedScreen(onBackClicked = onBackClicked)
    }
}