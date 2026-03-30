package com.nuttyknot.feelingswheel.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nuttyknot.feelingswheel.R
import com.nuttyknot.feelingswheel.data.model.EmotionSegment
import com.nuttyknot.feelingswheel.ui.components.FeelingsWheel
import com.nuttyknot.feelingswheel.ui.components.SelectionPanel
import com.nuttyknot.feelingswheel.viewmodel.WheelUiState
import kotlinx.coroutines.delay

@Suppress("LongMethod")
@Composable
fun FeelingsWheelScreen(
    uiState: WheelUiState,
    onSegmentTapped: (EmotionSegment) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    initialRotation: Float = 0f,
    onSettingsClick: () -> Unit = {},
    onOnboardingDismissed: () -> Unit = {},
) {
    if (uiState.showOnboarding) {
        LaunchedEffect(Unit) {
            delay(3000L)
            onOnboardingDismissed()
        }
    }

    Box(modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        FeelingsWheel(
            segments = uiState.segments,
            selectedSegmentId = uiState.selectedEmotion?.segment?.id,
            onSegmentTapped = { segment ->
                if (uiState.showOnboarding) onOnboardingDismissed()
                onSegmentTapped(segment)
            },
            initialRotation = initialRotation,
            modifier = Modifier.fillMaxSize(),
        )

        SelectionPanel(
            selectedEmotion = uiState.selectedEmotion,
            onDismiss = onDismiss,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
        )

        AnimatedVisibility(
            visible = uiState.showOnboarding,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.Center),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier =
                    Modifier
                        .background(
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.9f),
                            shape = MaterialTheme.shapes.medium,
                        ).padding(horizontal = 20.dp, vertical = 12.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(20.dp),
                )
                Text(
                    text = stringResource(R.string.onboarding_hint),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }

        FilledTonalIconButton(
            onClick = onSettingsClick,
            modifier =
                Modifier
                    .align(Alignment.TopEnd)
                    .statusBarsPadding()
                    .padding(8.dp),
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = stringResource(R.string.settings),
            )
        }
    }
}
