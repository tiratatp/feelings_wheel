package com.nuttyknot.feelingswheel.data.model

import androidx.compose.ui.graphics.Color

data class EmotionColors(
    val coreColor: Color,
    val middleColor: Color,
    val outerColor: Color,
    val useDarkText: Boolean = false,
) {
    fun colorForLayer(layer: WheelLayer): Color =
        when (layer) {
            WheelLayer.CORE -> coreColor
            WheelLayer.MIDDLE -> middleColor
            WheelLayer.OUTER -> outerColor
        }
}
