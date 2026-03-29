package com.nuttyknot.feelingswheel.data.model

import androidx.compose.ui.graphics.Color

data class WheelPalette(
    val name: String,
    val colors: Map<CoreEmotion, EmotionColors>,
) {
    fun colorsFor(emotion: CoreEmotion): EmotionColors = colors[emotion] ?: error("Missing colors for $emotion")

    companion object {
        val Classic =
            WheelPalette(
                name = "Classic",
                colors =
                    mapOf(
                        CoreEmotion.HAPPY to
                            EmotionColors(
                                Color(0xFFF9A825),
                                Color(0xFFFBC02D),
                                Color(0xFFFDD835),
                                useDarkText = true,
                            ),
                        CoreEmotion.SAD to
                            EmotionColors(
                                Color(0xFF1565C0),
                                Color(0xFF1E88E5),
                                Color(0xFF42A5F5),
                            ),
                        CoreEmotion.ANGRY to
                            EmotionColors(
                                Color(0xFFC62828),
                                Color(0xFFE53935),
                                Color(0xFFEF5350),
                            ),
                        CoreEmotion.FEARFUL to
                            EmotionColors(
                                Color(0xFF00695C),
                                Color(0xFF00897B),
                                Color(0xFF26A69A),
                            ),
                        CoreEmotion.DISGUSTED to
                            EmotionColors(
                                Color(0xFF827717),
                                Color(0xFF9E9D24),
                                Color(0xFFBDBB2E),
                                useDarkText = true,
                            ),
                        CoreEmotion.SURPRISED to
                            EmotionColors(
                                Color(0xFFE65100),
                                Color(0xFFEF6C00),
                                Color(0xFFF57C00),
                                useDarkText = true,
                            ),
                        CoreEmotion.BAD to
                            EmotionColors(
                                Color(0xFF4A148C),
                                Color(0xFF6A1B9A),
                                Color(0xFF7B1FA2),
                            ),
                    ),
            )

        val Pastel =
            WheelPalette(
                name = "Pastel",
                colors =
                    mapOf(
                        CoreEmotion.HAPPY to
                            EmotionColors(
                                Color(0xFFFFD9A0),
                                Color(0xFFFFE4B8),
                                Color(0xFFFFFED0),
                                useDarkText = true,
                            ),
                        CoreEmotion.SAD to
                            EmotionColors(
                                Color(0xFFA0C4E8),
                                Color(0xFFB5D4F0),
                                Color(0xFFC8E2F8),
                                useDarkText = true,
                            ),
                        CoreEmotion.ANGRY to
                            EmotionColors(
                                Color(0xFFF0A8A8),
                                Color(0xFFF5BFBF),
                                Color(0xFFFAD4D4),
                                useDarkText = true,
                            ),
                        CoreEmotion.FEARFUL to
                            EmotionColors(
                                Color(0xFFA0D8CF),
                                Color(0xFFB5E3DC),
                                Color(0xFFC8EDE7),
                                useDarkText = true,
                            ),
                        CoreEmotion.DISGUSTED to
                            EmotionColors(
                                Color(0xFFD4D89A),
                                Color(0xFFDFE3B0),
                                Color(0xFFE8ECC4),
                                useDarkText = true,
                            ),
                        CoreEmotion.SURPRISED to
                            EmotionColors(
                                Color(0xFFFFB89A),
                                Color(0xFFFFC8B0),
                                Color(0xFFFFD8C4),
                                useDarkText = true,
                            ),
                        CoreEmotion.BAD to
                            EmotionColors(
                                Color(0xFFC8A0E0),
                                Color(0xFFD4B5E8),
                                Color(0xFFE0C8F0),
                                useDarkText = true,
                            ),
                    ),
            )
    }
}
