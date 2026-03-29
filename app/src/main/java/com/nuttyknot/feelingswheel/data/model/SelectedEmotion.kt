package com.nuttyknot.feelingswheel.data.model

data class SelectedEmotion(
    val segment: EmotionSegment,
    val coreName: String,
    val middleName: String? = null,
    val outerName: String? = null,
)
