package com.nuttyknot.feelingswheel.data.model

data class MiddleEmotionDef(
    val label: String,
    val outerLabels: List<String>,
)

data class EmotionHierarchy(
    val coreLabels: Map<CoreEmotion, String>,
    val middleEmotions: Map<CoreEmotion, List<MiddleEmotionDef>>,
)
