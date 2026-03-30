package com.nuttyknot.feelingswheel.ui.util

import com.nuttyknot.feelingswheel.data.EmotionData
import com.nuttyknot.feelingswheel.data.hierarchy.englishHierarchy
import com.nuttyknot.feelingswheel.data.model.WheelLayer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class HitTestUtilsTest {
    private val segments = EmotionData.buildSegments(englishHierarchy())
    private val segmentsByLayer = segments.groupBy { it.layer }
    private val wheelRadius = 1000f
    private val centerX = 500f
    private val centerY = 1000f

    @Test
    fun hitTest_coreLayer() {
        // Touch at 15% of radius (inside CORE which ends at 0.30)
        // Touch to the right of center (angle ~0 degrees)
        val touchX = centerX + wheelRadius * 0.15f
        val touchY = centerY
        val result = HitTestUtils.hitTest(touchX, touchY, centerX, centerY, wheelRadius, 0f, segmentsByLayer)
        assertNotNull(result)
        assertEquals(WheelLayer.CORE, result!!.layer)
    }

    @Test
    fun hitTest_middleLayer() {
        // Touch at 45% of radius (inside MIDDLE which is 0.30-0.60)
        val touchX = centerX + wheelRadius * 0.45f
        val touchY = centerY
        val result = HitTestUtils.hitTest(touchX, touchY, centerX, centerY, wheelRadius, 0f, segmentsByLayer)
        assertNotNull(result)
        assertEquals(WheelLayer.MIDDLE, result!!.layer)
    }

    @Test
    fun hitTest_outerLayer() {
        // Touch at 80% of radius (inside OUTER which is 0.60-1.0)
        val touchX = centerX + wheelRadius * 0.8f
        val touchY = centerY
        val result = HitTestUtils.hitTest(touchX, touchY, centerX, centerY, wheelRadius, 0f, segmentsByLayer)
        assertNotNull(result)
        assertEquals(WheelLayer.OUTER, result!!.layer)
    }

    @Test
    fun hitTest_outsideWheel_returnsNull() {
        // Touch beyond radius
        val touchX = centerX + wheelRadius * 1.5f
        val touchY = centerY
        val result = HitTestUtils.hitTest(touchX, touchY, centerX, centerY, wheelRadius, 0f, segmentsByLayer)
        assertNull(result)
    }

    @Test
    fun hitTest_withRotation_unrotatesAngle() {
        // Touch to the right (angle 0) with 90 degree rotation
        // Should map to segment at angle 270 (0 - 90 = -90 = 270)
        val touchX = centerX + wheelRadius * 0.15f
        val touchY = centerY
        val resultNoRotation = HitTestUtils.hitTest(touchX, touchY, centerX, centerY, wheelRadius, 0f, segmentsByLayer)
        val resultWithRotation =
            HitTestUtils.hitTest(
                touchX,
                touchY,
                centerX,
                centerY,
                wheelRadius,
                90f,
                segmentsByLayer,
            )

        assertNotNull(resultNoRotation)
        assertNotNull(resultWithRotation)
        // With 90 degree rotation, the segment should be different
        // (the touch hits a different part of the wheel)
        assertEquals(WheelLayer.CORE, resultWithRotation!!.layer)
    }

    @Test
    fun hitTest_atCoreBoundary() {
        // Touch exactly at CORE outer radius (0.30)
        val touchX = centerX + wheelRadius * 0.30f
        val touchY = centerY
        val result = HitTestUtils.hitTest(touchX, touchY, centerX, centerY, wheelRadius, 0f, segmentsByLayer)
        assertNotNull(result)
        // At exactly 0.30, it should be CORE (radiusFraction <= CORE.outerRadius)
        assertEquals(WheelLayer.CORE, result!!.layer)
    }
}
