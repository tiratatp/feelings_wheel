package com.nuttyknot.feelingswheel.ui

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.Density
import com.android.resources.ScreenOrientation
import com.nuttyknot.feelingswheel.data.EmotionData
import com.nuttyknot.feelingswheel.data.hierarchy.englishHierarchy
import com.nuttyknot.feelingswheel.data.model.SelectedEmotion
import com.nuttyknot.feelingswheel.data.model.WheelLayer
import com.nuttyknot.feelingswheel.ui.screen.FeelingsWheelScreen
import com.nuttyknot.feelingswheel.ui.theme.FeelingsWheelTheme
import com.nuttyknot.feelingswheel.viewmodel.WheelUiState
import org.junit.Rule
import org.junit.Test

class FeelingsWheelTablet10ScreenshotTest {
    @get:Rule
    val paparazzi =
        Paparazzi(
            deviceConfig =
                DeviceConfig.PIXEL_C.copy(
                    screenWidth = 1080,
                    screenHeight = 1920,
                    density = Density.HIGH,
                    orientation = ScreenOrientation.LANDSCAPE,
                ),
            theme = "android:Theme.Material.NoActionBar.Fullscreen",
            renderingMode = SessionParams.RenderingMode.NORMAL,
            useDeviceResolution = true,
            maxPercentDifference = 1.0,
        )

    private val hierarchy = englishHierarchy()
    private val segments = EmotionData.buildSegments(hierarchy)

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
                coreName = hierarchy.coreLabels[outerSegment.coreEmotion] ?: outerSegment.coreEmotion.name,
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
}
