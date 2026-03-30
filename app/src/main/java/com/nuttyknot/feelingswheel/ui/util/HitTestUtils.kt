package com.nuttyknot.feelingswheel.ui.util

import com.nuttyknot.feelingswheel.data.model.EmotionSegment
import com.nuttyknot.feelingswheel.data.model.WheelLayer
import kotlin.math.sqrt

object HitTestUtils {
    /**
     * Find the segment at the given touch coordinates.
     * Returns null if no segment matches.
     *
     * @param touchX touch X in canvas coordinates
     * @param touchY touch Y in canvas coordinates
     * @param centerX center X of the wheel
     * @param centerY center Y of the wheel
     * @param wheelRadius outer radius of the wheel in pixels
     * @param rotationDegrees current rotation of the wheel
     * @param segments all segments to test against
     */
    fun hitTest(
        touchX: Float,
        touchY: Float,
        centerX: Float,
        centerY: Float,
        wheelRadius: Float,
        rotationDegrees: Float,
        segmentsByLayer: Map<WheelLayer, List<EmotionSegment>>,
    ): EmotionSegment? {
        val dx = touchX - centerX
        val dy = touchY - centerY
        val distance = sqrt(dx * dx + dy * dy)
        val radiusFraction = distance / wheelRadius

        if (radiusFraction > 1f) return null

        // Determine which layer was touched
        val layer =
            when {
                radiusFraction <= WheelLayer.CORE.outerRadius -> WheelLayer.CORE
                radiusFraction <= WheelLayer.MIDDLE.outerRadius -> WheelLayer.MIDDLE
                else -> WheelLayer.OUTER
            }

        // Compute un-rotated touch angle
        val touchAngle = AngleUtils.touchAngle(touchX, touchY, centerX, centerY)
        val unrotatedAngle = AngleUtils.normalize(touchAngle - rotationDegrees)

        // Look up segments directly from pre-grouped map (no filtering needed)
        return segmentsByLayer[layer]?.find { segment ->
            AngleUtils.containsAngle(unrotatedAngle, segment.startAngle, segment.sweepAngle)
        }
    }
}
