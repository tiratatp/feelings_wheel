package com.nuttyknot.feelingswheel.data.hierarchy

import com.nuttyknot.feelingswheel.data.model.CoreEmotion
import com.nuttyknot.feelingswheel.data.model.EmotionHierarchy
import com.nuttyknot.feelingswheel.data.model.MiddleEmotionDef

fun englishHierarchy(): EmotionHierarchy =
    EmotionHierarchy(
        coreLabels =
            mapOf(
                CoreEmotion.HAPPY to "Happy",
                CoreEmotion.SAD to "Sad",
                CoreEmotion.ANGRY to "Angry",
                CoreEmotion.FEARFUL to "Fearful",
                CoreEmotion.DISGUSTED to "Disgusted",
                CoreEmotion.SURPRISED to "Surprised",
                CoreEmotion.BAD to "Bad",
            ),
        middleEmotions =
            mapOf(
                CoreEmotion.HAPPY to
                    listOf(
                        MiddleEmotionDef("Playful", listOf("Aroused", "Cheeky")),
                        MiddleEmotionDef("Content", listOf("Free", "Joyful")),
                        MiddleEmotionDef("Interested", listOf("Curious", "Inquisitive")),
                        MiddleEmotionDef("Proud", listOf("Successful", "Confident")),
                        MiddleEmotionDef("Accepted", listOf("Respected", "Valued")),
                        MiddleEmotionDef("Powerful", listOf("Courageous", "Creative")),
                        MiddleEmotionDef("Peaceful", listOf("Loving", "Thankful")),
                        MiddleEmotionDef("Trusting", listOf("Sensitive", "Intimate")),
                        MiddleEmotionDef("Optimistic", listOf("Hopeful", "Inspired")),
                    ),
                CoreEmotion.SAD to
                    listOf(
                        MiddleEmotionDef("Lonely", listOf("Isolated", "Abandoned")),
                        MiddleEmotionDef("Vulnerable", listOf("Victimized", "Fragile")),
                        MiddleEmotionDef("Despair", listOf("Grief", "Powerless")),
                        MiddleEmotionDef("Guilty", listOf("Ashamed", "Remorseful")),
                        MiddleEmotionDef("Depressed", listOf("Inferior", "Empty")),
                        MiddleEmotionDef("Hurt", listOf("Embarrassed", "Disappointed")),
                    ),
                CoreEmotion.ANGRY to
                    listOf(
                        MiddleEmotionDef("Let Down", listOf("Betrayed", "Resentful")),
                        MiddleEmotionDef("Humiliated", listOf("Disrespected", "Ridiculed")),
                        MiddleEmotionDef("Bitter", listOf("Indignant", "Violated")),
                        MiddleEmotionDef("Mad", listOf("Furious", "Jealous")),
                        MiddleEmotionDef("Aggressive", listOf("Provoked", "Hostile")),
                        MiddleEmotionDef("Frustrated", listOf("Infuriated", "Annoyed")),
                        MiddleEmotionDef("Distant", listOf("Withdrawn", "Numb")),
                        MiddleEmotionDef("Critical", listOf("Skeptical", "Dismissive")),
                    ),
                CoreEmotion.FEARFUL to
                    listOf(
                        MiddleEmotionDef("Scared", listOf("Helpless", "Frightened")),
                        MiddleEmotionDef("Anxious", listOf("Overwhelmed", "Worried")),
                        MiddleEmotionDef("Insecure", listOf("Inadequate", "Inferior")),
                        MiddleEmotionDef("Weak", listOf("Worthless", "Insignificant")),
                        MiddleEmotionDef("Rejected", listOf("Excluded", "Persecuted")),
                        MiddleEmotionDef("Threatened", listOf("Nervous", "Exposed")),
                    ),
                CoreEmotion.DISGUSTED to
                    listOf(
                        MiddleEmotionDef("Disapproving", listOf("Judgmental", "Embarrassed")),
                        MiddleEmotionDef("Disappointed", listOf("Appalled", "Revolted")),
                        MiddleEmotionDef("Awful", listOf("Nauseated", "Detestable")),
                        MiddleEmotionDef("Repelled", listOf("Horrified", "Hesitant")),
                    ),
                CoreEmotion.SURPRISED to
                    listOf(
                        MiddleEmotionDef("Startled", listOf("Shocked", "Dismayed")),
                        MiddleEmotionDef("Confused", listOf("Disillusioned", "Perplexed")),
                        MiddleEmotionDef("Amazed", listOf("Astonished", "Awe")),
                        MiddleEmotionDef("Excited", listOf("Eager", "Energetic")),
                    ),
                CoreEmotion.BAD to
                    listOf(
                        MiddleEmotionDef("Bored", listOf("Indifferent", "Apathetic")),
                        MiddleEmotionDef("Busy", listOf("Pressured", "Rushed")),
                        MiddleEmotionDef("Stressed", listOf("Overwhelmed", "Out of Control")),
                        MiddleEmotionDef("Tired", listOf("Sleepy", "Unfocused")),
                    ),
            ),
    )
