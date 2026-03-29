package com.nuttyknot.feelingswheel.data.model

import androidx.compose.ui.graphics.Color

data class EmotionSegment(
    val id: String,
    val label: String,
    val layer: WheelLayer,
    val coreEmotion: CoreEmotion,
    val startAngle: Float,
    val sweepAngle: Float,
    val color: Color,
    val useDarkText: Boolean,
) {
    val endAngle: Float get() = startAngle + sweepAngle
    val midAngle: Float get() = startAngle + sweepAngle / 2f
}
