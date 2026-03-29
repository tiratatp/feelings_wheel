package com.nuttyknot.feelingswheel.viewmodel

import androidx.lifecycle.ViewModel
import com.nuttyknot.feelingswheel.data.EmotionData
import com.nuttyknot.feelingswheel.data.model.EmotionSegment
import com.nuttyknot.feelingswheel.data.model.SelectedEmotion
import com.nuttyknot.feelingswheel.data.model.WheelLayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class WheelUiState(
    val segments: List<EmotionSegment> = emptyList(),
    val selectedEmotion: SelectedEmotion? = null,
)

class FeelingsWheelViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(WheelUiState())
    val uiState: StateFlow<WheelUiState> = _uiState.asStateFlow()

    init {
        _uiState.update { it.copy(segments = EmotionData.buildSegments()) }
    }

    fun selectSegment(segment: EmotionSegment) {
        val segments = _uiState.value.segments

        val selected =
            when (segment.layer) {
                WheelLayer.CORE ->
                    SelectedEmotion(
                        segment = segment,
                        coreName = segment.label,
                    )
                WheelLayer.MIDDLE ->
                    SelectedEmotion(
                        segment = segment,
                        coreName = segment.coreEmotion.label,
                        middleName = segment.label,
                    )
                WheelLayer.OUTER -> {
                    // Find parent middle by checking which middle segment contains this outer's angle
                    val middleSegment =
                        segments.find { s ->
                            s.layer == WheelLayer.MIDDLE &&
                                s.coreEmotion == segment.coreEmotion &&
                                segment.startAngle >= s.startAngle &&
                                segment.startAngle < s.startAngle + s.sweepAngle
                        }
                    SelectedEmotion(
                        segment = segment,
                        coreName = segment.coreEmotion.label,
                        middleName = middleSegment?.label,
                        outerName = segment.label,
                    )
                }
            }

        _uiState.update { it.copy(selectedEmotion = selected) }
    }

    fun clearSelection() {
        _uiState.update { it.copy(selectedEmotion = null) }
    }
}
