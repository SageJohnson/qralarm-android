package com.sweak.qralarm.features.time_saved

sealed class TimeSavedScreenUserEvent {
    data object OnBackClicked : TimeSavedScreenUserEvent()
}