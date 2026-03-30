package com.nuttyknot.feelingswheel.ui.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AngleUtilsTest {
    @Test
    fun normalize_positiveAngle() {
        assertEquals(45f, AngleUtils.normalize(45f), 0.001f)
    }

    @Test
    fun normalize_negativeAngle() {
        assertEquals(270f, AngleUtils.normalize(-90f), 0.001f)
    }

    @Test
    fun normalize_multipleOf360() {
        assertEquals(0f, AngleUtils.normalize(720f), 0.001f)
    }

    @Test
    fun normalize_zero() {
        assertEquals(0f, AngleUtils.normalize(0f), 0.001f)
    }

    @Test
    fun normalize_negativeMultipleOf360() {
        assertEquals(0f, AngleUtils.normalize(-360f), 0.001f)
    }

    @Test
    fun normalize_largeNegative() {
        assertEquals(350f, AngleUtils.normalize(-370f), 0.001f)
    }

    @Test
    fun containsAngle_withinRange() {
        assertTrue(AngleUtils.containsAngle(45f, 30f, 30f))
    }

    @Test
    fun containsAngle_atStartBoundary() {
        assertTrue(AngleUtils.containsAngle(30f, 30f, 30f))
    }

    @Test
    fun containsAngle_atEndBoundary() {
        assertTrue(AngleUtils.containsAngle(60f, 30f, 30f))
    }

    @Test
    fun containsAngle_outsideRange() {
        assertFalse(AngleUtils.containsAngle(90f, 30f, 30f))
    }

    @Test
    fun containsAngle_wrappingAround360() {
        assertTrue(AngleUtils.containsAngle(10f, 350f, 30f))
    }

    @Test
    fun containsAngle_wrappingAround360_atStart() {
        assertTrue(AngleUtils.containsAngle(350f, 350f, 30f))
    }

    @Test
    fun containsAngle_wrappingAround360_outside() {
        assertFalse(AngleUtils.containsAngle(200f, 350f, 30f))
    }

    @Test
    fun touchAngle_right() {
        val angle = AngleUtils.touchAngle(200f, 100f, 100f, 100f)
        assertEquals(0f, angle, 0.001f)
    }

    @Test
    fun touchAngle_down() {
        val angle = AngleUtils.touchAngle(100f, 200f, 100f, 100f)
        assertEquals(90f, angle, 0.001f)
    }

    @Test
    fun touchAngle_left() {
        val angle = AngleUtils.touchAngle(0f, 100f, 100f, 100f)
        assertEquals(180f, angle, 0.001f)
    }

    @Test
    fun touchAngle_up() {
        val angle = AngleUtils.touchAngle(100f, 0f, 100f, 100f)
        assertEquals(270f, angle, 0.001f)
    }
}
