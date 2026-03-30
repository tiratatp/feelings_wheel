package com.nuttyknot.feelingswheel.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nuttyknot.feelingswheel.R
import com.nuttyknot.feelingswheel.data.model.EmotionSegment
import com.nuttyknot.feelingswheel.ui.components.FeelingsWheel
import com.nuttyknot.feelingswheel.ui.components.SelectionPanel
import com.nuttyknot.feelingswheel.viewmodel.WheelUiState

@Composable
fun FeelingsWheelScreen(
    uiState: WheelUiState,
    onSegmentTapped: (EmotionSegment) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    initialRotation: Float = 0f,
    onSettingsClick: () -> Unit = {},
) {
    Box(modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        FeelingsWheel(
            segments = uiState.segments,
            selectedSegmentId = uiState.selectedEmotion?.segment?.id,
            onSegmentTapped = onSegmentTapped,
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

        IconButton(
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
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            )
        }
    }
}
