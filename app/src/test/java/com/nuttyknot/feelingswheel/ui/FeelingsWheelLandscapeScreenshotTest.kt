package com.nuttyknot.feelingswheel.ui

import app.cash.paparazzi.DeviceConfig.Companion.PIXEL_5
import app.cash.paparazzi.Paparazzi
import com.android.resources.ScreenOrientation
import com.nuttyknot.feelingswheel.data.EmotionData
import com.nuttyknot.feelingswheel.data.hierarchy.englishHierarchy
import com.nuttyknot.feelingswheel.data.model.CoreEmotion
import com.nuttyknot.feelingswheel.data.model.SelectedEmotion
import com.nuttyknot.feelingswheel.data.model.WheelLayer
import com.nuttyknot.feelingswheel.ui.screen.FeelingsWheelScreen
import com.nuttyknot.feelingswheel.ui.theme.FeelingsWheelTheme
import com.nuttyknot.feelingswheel.viewmodel.WheelUiState
import org.junit.Rule
import org.junit.Test

class FeelingsWheelLandscapeScreenshotTest {
    @get:Rule
    val paparazzi =
        Paparazzi(
            deviceConfig =
                PIXEL_5.copy(
                    screenWidth = PIXEL_5.screenHeight,
                    screenHeight = PIXEL_5.screenWidth,
                    orientation = ScreenOrientation.LANDSCAPE,
                ),
            maxPercentDifference = 1.0,
        )

    private val hierarchy = englishHierarchy()
    private val segments = EmotionData.buildSegments(hierarchy)

    @Test
    fun defaultStateLandscape() {
        paparazzi.snapshot {
            FeelingsWheelTheme(dynamicColor = false) {
                FeelingsWheelScreen(
                    uiState = WheelUiState(segments = segments),
                    onSegmentTapped = {},
                    onDismiss = {},
                    onSettingsClick = {},
                )
            }
        }
    }

    @Test
    fun selectedEmotionLandscape() {
        val coreSegment =
            segments.first { it.layer == WheelLayer.CORE && it.coreEmotion == CoreEmotion.HAPPY }
        val selected =
            SelectedEmotion(
                segment = coreSegment,
                coreName = hierarchy.coreLabels[CoreEmotion.HAPPY] ?: "Happy",
            )
        paparazzi.snapshot {
            FeelingsWheelTheme(dynamicColor = false) {
                FeelingsWheelScreen(
                    uiState = WheelUiState(segments = segments, selectedEmotion = selected),
                    onSegmentTapped = {},
                    onDismiss = {},
                    onSettingsClick = {},
                )
            }
        }
    }
}
