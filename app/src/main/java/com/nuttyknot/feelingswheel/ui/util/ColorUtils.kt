package com.nuttyknot.feelingswheel.ui.util

import androidx.compose.ui.graphics.Color

fun Color.darken(factor: Float): Color =
    copy(
        red = red * factor,
        green = green * factor,
        blue = blue * factor,
    )
