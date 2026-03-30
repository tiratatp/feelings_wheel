package com.nuttyknot.feelingswheel.data.model

import androidx.compose.ui.graphics.Color
import com.nuttyknot.feelingswheel.ui.util.darken

data class EmotionSegment(
    val id: String,
    val label: String,
    val layer: WheelLayer,
    val coreEmotion: CoreEmotion,
    val startAngle: Float,
    val sweepAngle: Float,
    val color: Color,
    val useDarkText: Boolean,
    val description: String = "",
) {
    val endAngle: Float get() = startAngle + sweepAngle
    val midAngle: Float get() = startAngle + sweepAngle / 2f
    val darkenedColor: Color = color.darken(0.75f)
}
