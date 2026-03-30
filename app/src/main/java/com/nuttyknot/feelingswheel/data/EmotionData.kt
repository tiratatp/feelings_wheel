package com.nuttyknot.feelingswheel.data

import com.nuttyknot.feelingswheel.data.hierarchy.englishHierarchy
import com.nuttyknot.feelingswheel.data.model.CoreEmotion
import com.nuttyknot.feelingswheel.data.model.EmotionHierarchy
import com.nuttyknot.feelingswheel.data.model.EmotionSegment
import com.nuttyknot.feelingswheel.data.model.WheelLayer
import com.nuttyknot.feelingswheel.data.model.WheelPalette

object EmotionData {
    fun buildSegments(
        hierarchy: EmotionHierarchy = englishHierarchy(),
        palette: WheelPalette = WheelPalette.Pastel,
    ): List<EmotionSegment> {
        val segments = mutableListOf<EmotionSegment>()
        val coreSweep = 360f / CoreEmotion.entries.size

        CoreEmotion.entries.forEachIndexed { coreIndex, core ->
            val coreStart = coreIndex * coreSweep
            val colors = palette.colorsFor(core)

            segments.add(
                EmotionSegment(
                    id = "core_${core.name}",
                    label = hierarchy.coreLabels[core] ?: core.name,
                    layer = WheelLayer.CORE,
                    coreEmotion = core,
                    startAngle = coreStart,
                    sweepAngle = coreSweep,
                    color = colors.coreColor,
                    useDarkText = colors.useDarkText,
                ),
            )

            val midList = hierarchy.middleEmotions[core] ?: emptyList()
            val midSweep = if (midList.isNotEmpty()) coreSweep / midList.size else 0f

            midList.forEachIndexed { midIndex, mid ->
                val midStart = coreStart + midIndex * midSweep

                segments.add(
                    EmotionSegment(
                        id = "mid_${core.name}_$midIndex",
                        label = mid.label,
                        layer = WheelLayer.MIDDLE,
                        coreEmotion = core,
                        startAngle = midStart,
                        sweepAngle = midSweep,
                        color = colors.middleColor,
                        useDarkText = colors.useDarkText,
                    ),
                )

                val outerList = mid.outerLabels
                val outerSweep = if (outerList.isNotEmpty()) midSweep / outerList.size else 0f

                outerList.forEachIndexed { outerIndex, outerLabel ->
                    val outerStart = midStart + outerIndex * outerSweep

                    segments.add(
                        EmotionSegment(
                            id = "outer_${core.name}_${midIndex}_$outerIndex",
                            label = outerLabel,
                            layer = WheelLayer.OUTER,
                            coreEmotion = core,
                            startAngle = outerStart,
                            sweepAngle = outerSweep,
                            color = colors.outerColor,
                            useDarkText = colors.useDarkText,
                        ),
                    )
                }
            }
        }

        return segments
    }
}
