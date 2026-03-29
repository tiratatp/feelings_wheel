package com.nuttyknot.feelingswheel.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import com.nuttyknot.feelingswheel.data.model.EmotionSegment
import com.nuttyknot.feelingswheel.ui.util.AngleUtils
import com.nuttyknot.feelingswheel.ui.util.HitTestUtils
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.sqrt

@Composable
fun FeelingsWheel(
    segments: List<EmotionSegment>,
    selectedSegmentId: String?,
    onSegmentTapped: (EmotionSegment) -> Unit,
    modifier: Modifier = Modifier,
    initialRotation: Float = 0f,
) {
    var rotation by remember { mutableFloatStateOf(initialRotation) }
    val scope = rememberCoroutineScope()
    val flingAnimatable = remember { Animatable(initialRotation) }
    var activeFlingJob by remember { mutableStateOf<Job?>(null) }
    val segmentsByLayer = remember(segments) { segments.groupBy { it.layer } }

    Canvas(
        modifier =
            modifier
                .fillMaxSize()
                .pointerInput(segments) {
                    val velocityTracker = VelocityTracker()
                    val dragThreshold = 10f

                    awaitEachGesture {
                        val down = awaitFirstDown()

                        // Cancel any ongoing fling
                        activeFlingJob?.cancel()
                        activeFlingJob = null

                        val centerX = size.width / 2f
                        val centerY = size.height.toFloat()

                        var prevAngle =
                            AngleUtils.touchAngle(
                                down.position.x,
                                down.position.y,
                                centerX,
                                centerY,
                            )
                        var totalDragDistance = 0f
                        var isDragging = false

                        velocityTracker.resetTracking()
                        velocityTracker.addPosition(
                            down.uptimeMillis,
                            Offset(rotation, 0f),
                        )

                        var continueTracking = true
                        while (continueTracking) {
                            val event = awaitPointerEvent()
                            val change = event.changes.firstOrNull()
                            if (change == null || !change.pressed) {
                                continueTracking = false
                                continue
                            }

                            val currentAngle =
                                AngleUtils.touchAngle(
                                    change.position.x,
                                    change.position.y,
                                    centerX,
                                    centerY,
                                )

                            var angleDelta = currentAngle - prevAngle
                            if (angleDelta > 180f) angleDelta -= 360f
                            if (angleDelta < -180f) angleDelta += 360f

                            val moveDistance =
                                change.positionChange().let {
                                    sqrt(it.x * it.x + it.y * it.y)
                                }
                            totalDragDistance += moveDistance

                            if (totalDragDistance > dragThreshold) {
                                isDragging = true
                            }

                            if (isDragging) {
                                rotation += angleDelta
                                change.consume()
                            }

                            prevAngle = currentAngle
                            velocityTracker.addPosition(
                                change.uptimeMillis,
                                Offset(rotation, 0f),
                            )
                        }

                        if (!isDragging) {
                            // Tap - hit test
                            val wheelRadius = size.height.toFloat()
                            val hitSegment =
                                HitTestUtils.hitTest(
                                    touchX = down.position.x,
                                    touchY = down.position.y,
                                    centerX = size.width / 2f,
                                    centerY = size.height.toFloat(),
                                    wheelRadius = wheelRadius,
                                    rotationDegrees = rotation,
                                    segments = segments,
                                )
                            if (hitSegment != null) {
                                onSegmentTapped(hitSegment)
                            }
                        } else {
                            // Fling
                            val velocity = velocityTracker.calculateVelocity()
                            val angularVelocity = velocity.x
                            if (abs(angularVelocity) > 50f) {
                                activeFlingJob =
                                    scope.launch {
                                        flingAnimatable.snapTo(rotation)
                                        flingAnimatable.animateDecay(
                                            initialVelocity = angularVelocity,
                                            animationSpec = exponentialDecay(frictionMultiplier = 2f),
                                        ) {
                                            rotation = value
                                        }
                                    }
                            }
                        }
                    }
                },
    ) {
        val center = Offset(size.width / 2f, size.height)
        val wheelRadius = size.height

        drawWheel(
            segments = segments,
            segmentsByLayer = segmentsByLayer,
            rotationDegrees = rotation,
            wheelRadius = wheelRadius,
            center = center,
            selectedSegmentId = selectedSegmentId,
        )
    }
}
