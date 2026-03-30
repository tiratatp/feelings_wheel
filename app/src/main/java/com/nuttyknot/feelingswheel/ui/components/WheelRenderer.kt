package com.nuttyknot.feelingswheel.ui.components

import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import com.nuttyknot.feelingswheel.data.model.EmotionSegment
import com.nuttyknot.feelingswheel.data.model.WheelLayer
import kotlin.math.cos
import kotlin.math.sin

private val borderPaint =
    Paint().apply {
        style = Paint.Style.STROKE
        color = android.graphics.Color.WHITE
        strokeWidth = 2f
        isAntiAlias = true
    }

private val textPaintDark =
    Paint().apply {
        color = android.graphics.Color.parseColor("#333333")
        isAntiAlias = true
        typeface = Typeface.DEFAULT_BOLD
        textAlign = Paint.Align.CENTER
    }

private val textPaintLight =
    Paint().apply {
        color = android.graphics.Color.WHITE
        isAntiAlias = true
        typeface = Typeface.DEFAULT_BOLD
        textAlign = Paint.Align.CENTER
    }

fun DrawScope.drawWheel(
    segments: List<EmotionSegment>,
    segmentsByLayer: Map<WheelLayer, List<EmotionSegment>>,
    wheelRadius: Float,
    center: Offset,
    selectedSegmentId: String?,
) {
    // Draw segments layer by layer (core first, outer last) with batched borders
    WheelLayer.entries.reversed().forEach { layer ->
        val layerSegments = segmentsByLayer[layer] ?: emptyList()
        layerSegments.forEach { segment ->
            drawSegmentArc(segment, wheelRadius, center, segment.id == selectedSegmentId)
        }
        // Batch all borders for this layer into a single drawIntoCanvas
        drawIntoCanvas { canvas ->
            layerSegments.forEach { segment ->
                val outerR = segment.layer.outerRadius * wheelRadius
                val rect =
                    android.graphics.RectF(
                        center.x - outerR,
                        center.y - outerR,
                        center.x + outerR,
                        center.y + outerR,
                    )
                canvas.nativeCanvas.drawArc(
                    rect,
                    segment.startAngle,
                    segment.sweepAngle,
                    true,
                    borderPaint,
                )
            }
        }
    }

    // Draw text on top
    drawIntoCanvas { canvas ->
        segments.forEach { segment ->
            drawSegmentText(canvas.nativeCanvas, segment, wheelRadius, center)
        }
    }
}

private fun DrawScope.drawSegmentArc(
    segment: EmotionSegment,
    wheelRadius: Float,
    center: Offset,
    isSelected: Boolean,
) {
    val innerR = segment.layer.innerRadius * wheelRadius
    val outerR = segment.layer.outerRadius * wheelRadius

    val color = if (isSelected) segment.darkenedColor else segment.color

    if (innerR == 0f) {
        // Core segment — simple pie slice
        drawArc(
            color = color,
            startAngle = segment.startAngle,
            sweepAngle = segment.sweepAngle,
            useCenter = true,
            topLeft = Offset(center.x - outerR, center.y - outerR),
            size = Size(outerR * 2, outerR * 2),
        )
    } else {
        // Ring segment — path-based to avoid white overdraw
        val path =
            Path().apply {
                arcTo(
                    rect = Rect(center.x - outerR, center.y - outerR, center.x + outerR, center.y + outerR),
                    startAngleDegrees = segment.startAngle,
                    sweepAngleDegrees = segment.sweepAngle,
                    forceMoveTo = true,
                )
                arcTo(
                    rect = Rect(center.x - innerR, center.y - innerR, center.x + innerR, center.y + innerR),
                    startAngleDegrees = segment.startAngle + segment.sweepAngle,
                    sweepAngleDegrees = -segment.sweepAngle,
                    forceMoveTo = false,
                )
                close()
            }
        drawPath(path, color)
    }
}

private fun drawSegmentText(
    canvas: android.graphics.Canvas,
    segment: EmotionSegment,
    wheelRadius: Float,
    center: Offset,
) {
    val innerR = segment.layer.innerRadius * wheelRadius
    val outerR = segment.layer.outerRadius * wheelRadius
    val midR = (innerR + outerR) / 2f
    val midAngle = segment.startAngle + segment.sweepAngle / 2f

    // Calculate text size based on layer
    val textSize =
        when (segment.layer) {
            WheelLayer.CORE -> wheelRadius * 0.05f
            WheelLayer.MIDDLE -> wheelRadius * 0.03f
            WheelLayer.OUTER -> wheelRadius * 0.022f
        }

    val paint = if (segment.useDarkText) textPaintDark else textPaintLight
    paint.textSize = textSize

    // Compute text position at the mid-point of the segment arc
    // using the same angle convention as drawArc (0° = 3 o'clock, CW)
    val angleRad = Math.toRadians(midAngle.toDouble())
    val textX = center.x + midR * cos(angleRad).toFloat()
    val textY = center.y + midR * sin(angleRad).toFloat()

    canvas.save()

    // Orient text radially (pointing outward from center)
    canvas.rotate(midAngle, textX, textY)

    canvas.drawText(segment.label, textX, textY + textSize / 3f, paint)

    canvas.restore()
}
