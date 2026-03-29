package com.nuttyknot.feelingswheel.data.model

enum class WheelLayer(
    val innerRadius: Float,
    val outerRadius: Float,
) {
    CORE(0f, 0.30f),
    MIDDLE(0.30f, 0.60f),
    OUTER(0.60f, 1.0f),
}
