package com.sweak.qralarm.features.time_saved

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sweak.qralarm.R
import com.sweak.qralarm.core.designsystem.icon.QRAlarmIcons
import com.sweak.qralarm.core.designsystem.theme.BlueZodiac
import com.sweak.qralarm.core.designsystem.theme.Jacarta
import com.sweak.qralarm.core.designsystem.theme.QRAlarmTheme
import com.sweak.qralarm.core.designsystem.theme.space

@Composable
fun TimeSavedScreen(
    onBackClicked: () -> Unit
) {
    val timeSavedViewModel = hiltViewModel<TimeSavedViewModel>()
    val timeSavedScreenState by timeSavedViewModel.state.collectAsStateWithLifecycle()

    TimeSavedScreenContent(
        state = timeSavedScreenState,
        onEvent = { event ->
            when (event) {
                is TimeSavedScreenUserEvent.OnBackClicked -> onBackClicked()
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeSavedScreenContent(
    state: TimeSavedScreenState,
    onEvent: (TimeSavedScreenUserEvent) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.time_saved),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onEvent(TimeSavedScreenUserEvent.OnBackClicked) }
                    ) {
                        Icon(
                            imageVector = QRAlarmIcons.BackArrow,
                            contentDescription =
                                stringResource(R.string.content_description_back_arrow_icon)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(listOf(Jacarta, BlueZodiac)))
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.space.large),
                modifier = Modifier
                    .padding(paddingValues = paddingValues)
                    .padding(MaterialTheme.space.medium)
                    .fillMaxWidth()
                    .align(Alignment.Center)
            ) {
                Text(
                    // TODO: Replace with real calculated time once dismissal tracking exists
                    text = stringResource(R.string.time_saved_headline),
                    style = MaterialTheme.typography.displayLarge,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "0h 0m",
                    style = MaterialTheme.typography.displayLarge,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = MaterialTheme.space.large)
                ) {
                    // TODO: Wire these up to real dismissal data
                    StatColumn(
                        value = state.totalDismissals.toString(),
                        label = stringResource(R.string.on_time_wakeups)
                    )
                    StatColumn(
                        value = state.currentStreak.toString(),
                        label = stringResource(R.string.day_streak)
                    )
                }

                Text(
                    text = stringResource(R.string.time_saved_coming_soon),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = MaterialTheme.space.large)
                )
            }
        }
    }
}

@Composable
private fun StatColumn(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.displayLarge,
            color = Color.White
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White.copy(alpha = 0.7f)
        )
    }
}

@Preview
@Composable
private fun TimeSavedScreenContentPreview() {
    QRAlarmTheme {
        TimeSavedScreenContent(
            state = TimeSavedScreenState(
                totalDismissals = 0,
                currentStreak = 0,
                timeSavedMinutes = 0L
            ),
            onEvent = {}
        )
    }
}