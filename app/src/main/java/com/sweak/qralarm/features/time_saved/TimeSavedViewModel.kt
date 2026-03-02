package com.sweak.qralarm.features.time_saved

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TimeSavedViewModel @Inject constructor() : ViewModel() {

    // TODO: Inject DismissalsRepository once alarm dismissal tracking is implemented
    private val _state = MutableStateFlow(
        TimeSavedScreenState(
            totalDismissals = 0,
            currentStreak = 0,
            timeSavedMinutes = 0L
        )
    )
    val state = _state.asStateFlow()

    fun onEvent(event: TimeSavedScreenUserEvent) {
        // TODO: Handle events once real data is wired in
    }
}