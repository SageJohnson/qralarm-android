package com.sweak.qralarm.features.time_saved

data class TimeSavedScreenState(
    // TODO: Replace with real data from alarm dismissal tracking
    val totalDismissals: Int = 0,
    val currentStreak: Int = 0,
    val timeSavedMinutes: Long = 0L
)