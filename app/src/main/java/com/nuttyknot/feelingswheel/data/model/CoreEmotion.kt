package com.nuttyknot.feelingswheel.data.model

import androidx.compose.ui.graphics.Color

enum class CoreEmotion(
    val label: String,
    val coreColor: Color,
    val middleColor: Color,
    val outerColor: Color,
    val useDarkText: Boolean = false,
) {
    HAPPY(
        label = "Happy",
        coreColor = Color(0xFFF9A825),
        middleColor = Color(0xFFFBC02D),
        outerColor = Color(0xFFFDD835),
        useDarkText = true,
    ),
    SAD(
        label = "Sad",
        coreColor = Color(0xFF1565C0),
        middleColor = Color(0xFF1E88E5),
        outerColor = Color(0xFF42A5F5),
    ),
    ANGRY(
        label = "Angry",
        coreColor = Color(0xFFC62828),
        middleColor = Color(0xFFE53935),
        outerColor = Color(0xFFEF5350),
    ),
    FEARFUL(
        label = "Fearful",
        coreColor = Color(0xFF00695C),
        middleColor = Color(0xFF00897B),
        outerColor = Color(0xFF26A69A),
    ),
    DISGUSTED(
        label = "Disgusted",
        coreColor = Color(0xFF827717),
        middleColor = Color(0xFF9E9D24),
        outerColor = Color(0xFFBDBB2E),
        useDarkText = true,
    ),
    SURPRISED(
        label = "Surprised",
        coreColor = Color(0xFFE65100),
        middleColor = Color(0xFFEF6C00),
        outerColor = Color(0xFFF57C00),
        useDarkText = true,
    ),
    BAD(
        label = "Bad",
        coreColor = Color(0xFF4A148C),
        middleColor = Color(0xFF6A1B9A),
        outerColor = Color(0xFF7B1FA2),
    ),
    ;

    fun colorForLayer(layer: WheelLayer): Color =
        when (layer) {
            WheelLayer.CORE -> coreColor
            WheelLayer.MIDDLE -> middleColor
            WheelLayer.OUTER -> outerColor
        }
}
