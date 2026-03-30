package com.nuttyknot.feelingswheel.data

import com.nuttyknot.feelingswheel.data.hierarchy.englishHierarchy
import com.nuttyknot.feelingswheel.data.hierarchy.thaiHierarchy
import com.nuttyknot.feelingswheel.data.model.WheelLayer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class EmotionDataTest {
    private val englishSegments = EmotionData.buildSegments(englishHierarchy())
    private val thaiSegments = EmotionData.buildSegments(thaiHierarchy())

    @Test
    fun englishSegmentCount() {
        val core = englishSegments.count { it.layer == WheelLayer.CORE }
        val middle = englishSegments.count { it.layer == WheelLayer.MIDDLE }
        val outer = englishSegments.count { it.layer == WheelLayer.OUTER }

        assertEquals(7, core)
        assertEquals(41, middle)
        assertEquals(82, outer)
        assertEquals(130, englishSegments.size)
    }

    @Test
    fun coreSegmentsSumTo360Degrees() {
        val coreSegments = englishSegments.filter { it.layer == WheelLayer.CORE }
        val totalSweep = coreSegments.sumOf { it.sweepAngle.toDouble() }
        assertEquals(360.0, totalSweep, 0.01)
    }

    @Test
    fun noGapsOrOverlaps_coreSegments() {
        val coreSegments =
            englishSegments
                .filter { it.layer == WheelLayer.CORE }
                .sortedBy { it.startAngle }

        for (i in 0 until coreSegments.size - 1) {
            val current = coreSegments[i]
            val next = coreSegments[i + 1]
            assertEquals(
                "Gap/overlap between ${current.label} and ${next.label}",
                current.startAngle + current.sweepAngle,
                next.startAngle,
                0.01f,
            )
        }
    }

    @Test
    fun middleSegmentsFallWithinParentCore() {
        val coreSegments = englishSegments.filter { it.layer == WheelLayer.CORE }
        val middleSegments = englishSegments.filter { it.layer == WheelLayer.MIDDLE }

        middleSegments.forEach { mid ->
            val parent = coreSegments.first { it.coreEmotion == mid.coreEmotion }
            assertTrue(
                "Middle '${mid.label}' start before core '${parent.label}'",
                mid.startAngle >= parent.startAngle - 0.01f,
            )
            assertTrue(
                "Middle '${mid.label}' end ${mid.endAngle} after core '${parent.label}' end ${parent.endAngle}",
                mid.endAngle <= parent.endAngle + 0.01f,
            )
        }
    }

    @Test
    fun outerSegmentsFallWithinParentMiddle() {
        val middleSegments = englishSegments.filter { it.layer == WheelLayer.MIDDLE }
        val outerSegments = englishSegments.filter { it.layer == WheelLayer.OUTER }

        outerSegments.forEach { outer ->
            val parent =
                middleSegments.first { mid ->
                    mid.coreEmotion == outer.coreEmotion &&
                        outer.midAngle >= mid.startAngle &&
                        outer.midAngle < mid.startAngle + mid.sweepAngle
                }
            assertTrue(
                "Outer '${outer.label}' start before middle '${parent.label}'",
                outer.startAngle >= parent.startAngle - 0.1f,
            )
            assertTrue(
                "Outer '${outer.label}' end after middle '${parent.label}'",
                outer.endAngle <= parent.endAngle + 0.1f,
            )
        }
    }

    @Test
    fun thaiHierarchyBuildsSuccessfully() {
        val core = thaiSegments.count { it.layer == WheelLayer.CORE }
        val middle = thaiSegments.count { it.layer == WheelLayer.MIDDLE }
        val outer = thaiSegments.count { it.layer == WheelLayer.OUTER }

        assertEquals(7, core)
        assertTrue("Expected Thai middle segments > 0", middle > 0)
        assertTrue("Expected Thai outer segments > 0", outer > 0)
        assertEquals(7 + middle + outer, thaiSegments.size)
    }

    @Test
    fun thaiCoreSegmentsSumTo360Degrees() {
        val coreSegments = thaiSegments.filter { it.layer == WheelLayer.CORE }
        val totalSweep = coreSegments.sumOf { it.sweepAngle.toDouble() }
        assertEquals(360.0, totalSweep, 0.01)
    }
}
