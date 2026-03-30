package com.nuttyknot.feelingswheel.data.model

data class OuterEmotionDef(
    val label: String,
    val description: String = "",
)

data class MiddleEmotionDef(
    val label: String,
    val outerEmotions: List<OuterEmotionDef>,
    val description: String = "",
)

data class EmotionHierarchy(
    val coreLabels: Map<CoreEmotion, String>,
    val coreDescriptions: Map<CoreEmotion, String> = emptyMap(),
    val middleEmotions: Map<CoreEmotion, List<MiddleEmotionDef>>,
)
