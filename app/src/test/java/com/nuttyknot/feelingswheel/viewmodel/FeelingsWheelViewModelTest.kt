package com.nuttyknot.feelingswheel.viewmodel

import com.nuttyknot.feelingswheel.data.EmotionData
import com.nuttyknot.feelingswheel.data.hierarchy.englishHierarchy
import com.nuttyknot.feelingswheel.data.model.EmotionSegment
import com.nuttyknot.feelingswheel.data.model.SelectedEmotion
import com.nuttyknot.feelingswheel.data.model.WheelLayer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * Tests the segment selection logic used by FeelingsWheelViewModel.
 * Exercises the same breadcrumb-building algorithm without needing Android context.
 */
class FeelingsWheelViewModelTest {
    private val hierarchy = englishHierarchy()
    private val segments = EmotionData.buildSegments(hierarchy)
    private val middleSegments = segments.filter { it.layer == WheelLayer.MIDDLE }
    private val outerToMiddleMap: Map<String, EmotionSegment> =
        segments
            .filter { it.layer == WheelLayer.OUTER }
            .associateBy(
                keySelector = { it.id },
                valueTransform = { outer ->
                    middleSegments.first { mid ->
                        mid.coreEmotion == outer.coreEmotion &&
                            outer.startAngle >= mid.startAngle &&
                            outer.startAngle < mid.startAngle + mid.sweepAngle
                    }
                },
            )

    private fun buildSelectedEmotion(segment: EmotionSegment): SelectedEmotion {
        val coreLabel = hierarchy.coreLabels[segment.coreEmotion] ?: segment.coreEmotion.name
        return when (segment.layer) {
            WheelLayer.CORE ->
                SelectedEmotion(
                    segment = segment,
                    coreName = segment.label,
                )
            WheelLayer.MIDDLE ->
                SelectedEmotion(
                    segment = segment,
                    coreName = coreLabel,
                    middleName = segment.label,
                )
            WheelLayer.OUTER -> {
                val middleSegment = outerToMiddleMap[segment.id]
                SelectedEmotion(
                    segment = segment,
                    coreName = coreLabel,
                    middleName = middleSegment?.label,
                    outerName = segment.label,
                )
            }
        }
    }

    @Test
    fun selectCoreSegment_breadcrumbHasOnlyCoreName() {
        val coreSegment = segments.first { it.layer == WheelLayer.CORE }
        val selected = buildSelectedEmotion(coreSegment)

        assertEquals(coreSegment.label, selected.coreName)
        assertNull(selected.middleName)
        assertNull(selected.outerName)
    }

    @Test
    fun selectMiddleSegment_breadcrumbHasCoreAndMiddle() {
        val middleSegment = segments.first { it.layer == WheelLayer.MIDDLE }
        val selected = buildSelectedEmotion(middleSegment)

        val expectedCore = hierarchy.coreLabels[middleSegment.coreEmotion]
        assertEquals(expectedCore, selected.coreName)
        assertEquals(middleSegment.label, selected.middleName)
        assertNull(selected.outerName)
    }

    @Test
    fun selectOuterSegment_breadcrumbHasAllThreeLevels() {
        val outerSegment = segments.first { it.layer == WheelLayer.OUTER }
        val selected = buildSelectedEmotion(outerSegment)

        val expectedCore = hierarchy.coreLabels[outerSegment.coreEmotion]
        assertEquals(expectedCore, selected.coreName)
        assertNotNull(selected.middleName)
        assertEquals(outerSegment.label, selected.outerName)
    }

    @Test
    fun clearSelection_resetsToNull() {
        val state =
            WheelUiState(
                segments = segments,
                selectedEmotion = buildSelectedEmotion(segments.first()),
            )
        val clearedState = state.copy(selectedEmotion = null)

        assertNull(clearedState.selectedEmotion)
    }
}
