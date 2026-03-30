package com.nuttyknot.feelingswheel.ui

import app.cash.paparazzi.DeviceConfig.Companion.PIXEL_5
import app.cash.paparazzi.Paparazzi
import com.nuttyknot.feelingswheel.data.EmotionData
import com.nuttyknot.feelingswheel.data.model.CoreEmotion
import com.nuttyknot.feelingswheel.data.model.SelectedEmotion
import com.nuttyknot.feelingswheel.data.model.WheelLayer
import com.nuttyknot.feelingswheel.data.model.WheelPalette
import com.nuttyknot.feelingswheel.ui.screen.FeelingsWheelScreen
import com.nuttyknot.feelingswheel.ui.screen.SettingsScreen
import com.nuttyknot.feelingswheel.ui.theme.FeelingsWheelTheme
import com.nuttyknot.feelingswheel.viewmodel.WheelUiState
import org.junit.Rule
import org.junit.Test

class FeelingsWheelScreenshotTest {
    @get:Rule
    val paparazzi =
        Paparazzi(
            deviceConfig = PIXEL_5,
            maxPercentDifference = 1.0,
        )

    private val segments = EmotionData.buildSegments()

    @Test
    fun defaultState() {
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
    fun selectedCoreEmotion() {
        val coreSegment = segments.first { it.layer == WheelLayer.CORE && it.coreEmotion == CoreEmotion.HAPPY }
        val selected =
            SelectedEmotion(
                segment = coreSegment,
                coreName = coreSegment.coreEmotion.label,
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

    @Test
    fun selectedOuterEmotion() {
        val outerSegment = segments.first { it.layer == WheelLayer.OUTER }
        val middleSegment =
            segments.find {
                it.layer == WheelLayer.MIDDLE &&
                    it.coreEmotion == outerSegment.coreEmotion &&
                    outerSegment.startAngle >= it.startAngle &&
                    outerSegment.startAngle < it.startAngle + it.sweepAngle
            }
        val selected =
            SelectedEmotion(
                segment = outerSegment,
                coreName = outerSegment.coreEmotion.label,
                middleName = middleSegment?.label,
                outerName = outerSegment.label,
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

    @Test
    fun rotatedWheel() {
        paparazzi.snapshot {
            FeelingsWheelTheme(dynamicColor = false) {
                FeelingsWheelScreen(
                    uiState = WheelUiState(segments = segments),
                    onSegmentTapped = {},
                    onDismiss = {},
                    initialRotation = 120f,
                    onSettingsClick = {},
                )
            }
        }
    }

    @Test
    fun settingsScreenPastel() {
        paparazzi.snapshot {
            FeelingsWheelTheme(dynamicColor = false) {
                SettingsScreen(
                    currentPalette = WheelPalette.Pastel,
                    onPaletteSelected = {},
                    onBackClick = {},
                )
            }
        }
    }

    @Test
    fun settingsScreenClassic() {
        paparazzi.snapshot {
            FeelingsWheelTheme(dynamicColor = false) {
                SettingsScreen(
                    currentPalette = WheelPalette.Classic,
                    onPaletteSelected = {},
                    onBackClick = {},
                )
            }
        }
    }
}
