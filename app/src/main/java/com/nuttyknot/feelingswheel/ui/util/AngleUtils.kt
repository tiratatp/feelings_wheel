package com.nuttyknot.feelingswheel.ui.util

import kotlin.math.atan2

object AngleUtils {
    /** Normalize angle to [0, 360) range */
    fun normalize(degrees: Float): Float {
        val mod = degrees % 360f
        return if (mod < 0f) mod + 360f else mod
    }

    /** Check if a normalized angle falls within [startAngle, startAngle + sweepAngle) */
    fun containsAngle(
        testAngle: Float,
        startAngle: Float,
        sweepAngle: Float,
    ): Boolean {
        val normTest = normalize(testAngle)
        val normStart = normalize(startAngle)
        val normEnd = normalize(normStart + sweepAngle)

        return if (normStart < normEnd) {
            normTest in normStart..normEnd
        } else {
            // Wraps around 360
            normTest >= normStart || normTest <= normEnd
        }
    }

    /** Wrap an angle delta into the [-180, 180] range for shortest-path rotation. */
    fun normalizeAngleDelta(delta: Float): Float {
        var d = delta
        if (d > 180f) d -= 360f
        if (d < -180f) d += 360f
        return d
    }

    /** Compute angle in degrees from center (0,0) for a touch point. 0° = right, clockwise. */
    fun touchAngle(
        x: Float,
        y: Float,
        centerX: Float,
        centerY: Float,
    ): Float {
        val dx = x - centerX
        val dy = y - centerY
        val radians = atan2(dy.toDouble(), dx.toDouble())
        return normalize(Math.toDegrees(radians).toFloat())
    }
}
