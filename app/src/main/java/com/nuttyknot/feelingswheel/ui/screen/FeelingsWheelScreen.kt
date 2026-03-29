package com.nuttyknot.feelingswheel.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nuttyknot.feelingswheel.data.model.EmotionSegment
import com.nuttyknot.feelingswheel.ui.components.FeelingsWheel
import com.nuttyknot.feelingswheel.ui.components.SelectionPanel
import com.nuttyknot.feelingswheel.viewmodel.FeelingsWheelViewModel
import com.nuttyknot.feelingswheel.viewmodel.WheelUiState

@Composable
fun FeelingsWheelScreen(
    uiState: WheelUiState,
    onSegmentTapped: (EmotionSegment) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    initialRotation: Float = 0f,
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
    }
}

@Composable
fun FeelingsWheelScreen(viewModel: FeelingsWheelViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val initialRotation = remember { (0..360).random().toFloat() }
    FeelingsWheelScreen(
        uiState = uiState,
        onSegmentTapped = { viewModel.selectSegment(it) },
        onDismiss = { viewModel.clearSelection() },
        initialRotation = initialRotation,
    )
}
