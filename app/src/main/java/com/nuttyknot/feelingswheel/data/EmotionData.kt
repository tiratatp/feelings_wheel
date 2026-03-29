package com.nuttyknot.feelingswheel.data

import com.nuttyknot.feelingswheel.data.model.CoreEmotion
import com.nuttyknot.feelingswheel.data.model.EmotionSegment
import com.nuttyknot.feelingswheel.data.model.MiddleEmotion
import com.nuttyknot.feelingswheel.data.model.OuterEmotion
import com.nuttyknot.feelingswheel.data.model.WheelLayer

object EmotionData {
    private val middleEmotions: Map<CoreEmotion, List<MiddleEmotion>> =
        mapOf(
            CoreEmotion.HAPPY to
                listOf(
                    MiddleEmotion("Playful", CoreEmotion.HAPPY),
                    MiddleEmotion("Content", CoreEmotion.HAPPY),
                    MiddleEmotion("Interested", CoreEmotion.HAPPY),
                    MiddleEmotion("Proud", CoreEmotion.HAPPY),
                    MiddleEmotion("Accepted", CoreEmotion.HAPPY),
                    MiddleEmotion("Powerful", CoreEmotion.HAPPY),
                    MiddleEmotion("Peaceful", CoreEmotion.HAPPY),
                    MiddleEmotion("Trusting", CoreEmotion.HAPPY),
                    MiddleEmotion("Optimistic", CoreEmotion.HAPPY),
                ),
            CoreEmotion.SAD to
                listOf(
                    MiddleEmotion("Lonely", CoreEmotion.SAD),
                    MiddleEmotion("Vulnerable", CoreEmotion.SAD),
                    MiddleEmotion("Despair", CoreEmotion.SAD),
                    MiddleEmotion("Guilty", CoreEmotion.SAD),
                    MiddleEmotion("Depressed", CoreEmotion.SAD),
                    MiddleEmotion("Hurt", CoreEmotion.SAD),
                ),
            CoreEmotion.ANGRY to
                listOf(
                    MiddleEmotion("Let Down", CoreEmotion.ANGRY),
                    MiddleEmotion("Humiliated", CoreEmotion.ANGRY),
                    MiddleEmotion("Bitter", CoreEmotion.ANGRY),
                    MiddleEmotion("Mad", CoreEmotion.ANGRY),
                    MiddleEmotion("Aggressive", CoreEmotion.ANGRY),
                    MiddleEmotion("Frustrated", CoreEmotion.ANGRY),
                    MiddleEmotion("Distant", CoreEmotion.ANGRY),
                    MiddleEmotion("Critical", CoreEmotion.ANGRY),
                ),
            CoreEmotion.FEARFUL to
                listOf(
                    MiddleEmotion("Scared", CoreEmotion.FEARFUL),
                    MiddleEmotion("Anxious", CoreEmotion.FEARFUL),
                    MiddleEmotion("Insecure", CoreEmotion.FEARFUL),
                    MiddleEmotion("Weak", CoreEmotion.FEARFUL),
                    MiddleEmotion("Rejected", CoreEmotion.FEARFUL),
                    MiddleEmotion("Threatened", CoreEmotion.FEARFUL),
                ),
            CoreEmotion.DISGUSTED to
                listOf(
                    MiddleEmotion("Disapproving", CoreEmotion.DISGUSTED),
                    MiddleEmotion("Disappointed", CoreEmotion.DISGUSTED),
                    MiddleEmotion("Awful", CoreEmotion.DISGUSTED),
                    MiddleEmotion("Repelled", CoreEmotion.DISGUSTED),
                ),
            CoreEmotion.SURPRISED to
                listOf(
                    MiddleEmotion("Startled", CoreEmotion.SURPRISED),
                    MiddleEmotion("Confused", CoreEmotion.SURPRISED),
                    MiddleEmotion("Amazed", CoreEmotion.SURPRISED),
                    MiddleEmotion("Excited", CoreEmotion.SURPRISED),
                ),
            CoreEmotion.BAD to
                listOf(
                    MiddleEmotion("Bored", CoreEmotion.BAD),
                    MiddleEmotion("Busy", CoreEmotion.BAD),
                    MiddleEmotion("Stressed", CoreEmotion.BAD),
                    MiddleEmotion("Tired", CoreEmotion.BAD),
                ),
        )

    private val outerEmotions: Map<String, List<OuterEmotion>> =
        mapOf(
            // HAPPY children
            "Playful" to listOf(OuterEmotion("Aroused", "Playful"), OuterEmotion("Cheeky", "Playful")),
            "Content" to listOf(OuterEmotion("Free", "Content"), OuterEmotion("Joyful", "Content")),
            "Interested" to listOf(OuterEmotion("Curious", "Interested"), OuterEmotion("Inquisitive", "Interested")),
            "Proud" to listOf(OuterEmotion("Successful", "Proud"), OuterEmotion("Confident", "Proud")),
            "Accepted" to listOf(OuterEmotion("Respected", "Accepted"), OuterEmotion("Valued", "Accepted")),
            "Powerful" to listOf(OuterEmotion("Courageous", "Powerful"), OuterEmotion("Creative", "Powerful")),
            "Peaceful" to listOf(OuterEmotion("Loving", "Peaceful"), OuterEmotion("Thankful", "Peaceful")),
            "Trusting" to listOf(OuterEmotion("Sensitive", "Trusting"), OuterEmotion("Intimate", "Trusting")),
            "Optimistic" to listOf(OuterEmotion("Hopeful", "Optimistic"), OuterEmotion("Inspired", "Optimistic")),
            // SAD children
            "Lonely" to listOf(OuterEmotion("Isolated", "Lonely"), OuterEmotion("Abandoned", "Lonely")),
            "Vulnerable" to listOf(OuterEmotion("Victimized", "Vulnerable"), OuterEmotion("Fragile", "Vulnerable")),
            "Despair" to listOf(OuterEmotion("Grief", "Despair"), OuterEmotion("Powerless", "Despair")),
            "Guilty" to listOf(OuterEmotion("Ashamed", "Guilty"), OuterEmotion("Remorseful", "Guilty")),
            "Depressed" to listOf(OuterEmotion("Inferior", "Depressed"), OuterEmotion("Empty", "Depressed")),
            "Hurt" to listOf(OuterEmotion("Embarrassed", "Hurt"), OuterEmotion("Disappointed", "Hurt")),
            // ANGRY children
            "Let Down" to listOf(OuterEmotion("Betrayed", "Let Down"), OuterEmotion("Resentful", "Let Down")),
            "Humiliated" to listOf(OuterEmotion("Disrespected", "Humiliated"), OuterEmotion("Ridiculed", "Humiliated")),
            "Bitter" to listOf(OuterEmotion("Indignant", "Bitter"), OuterEmotion("Violated", "Bitter")),
            "Mad" to listOf(OuterEmotion("Furious", "Mad"), OuterEmotion("Jealous", "Mad")),
            "Aggressive" to listOf(OuterEmotion("Provoked", "Aggressive"), OuterEmotion("Hostile", "Aggressive")),
            "Frustrated" to listOf(OuterEmotion("Infuriated", "Frustrated"), OuterEmotion("Annoyed", "Frustrated")),
            "Distant" to listOf(OuterEmotion("Withdrawn", "Distant"), OuterEmotion("Numb", "Distant")),
            "Critical" to listOf(OuterEmotion("Skeptical", "Critical"), OuterEmotion("Dismissive", "Critical")),
            // FEARFUL children
            "Scared" to listOf(OuterEmotion("Helpless", "Scared"), OuterEmotion("Frightened", "Scared")),
            "Anxious" to listOf(OuterEmotion("Overwhelmed", "Anxious"), OuterEmotion("Worried", "Anxious")),
            "Insecure" to listOf(OuterEmotion("Inadequate", "Insecure"), OuterEmotion("Inferior", "Insecure")),
            "Weak" to listOf(OuterEmotion("Worthless", "Weak"), OuterEmotion("Insignificant", "Weak")),
            "Rejected" to listOf(OuterEmotion("Excluded", "Rejected"), OuterEmotion("Persecuted", "Rejected")),
            "Threatened" to listOf(OuterEmotion("Nervous", "Threatened"), OuterEmotion("Exposed", "Threatened")),
            // DISGUSTED children
            "Disapproving" to
                listOf(OuterEmotion("Judgmental", "Disapproving"), OuterEmotion("Embarrassed", "Disapproving")),
            "Disappointed" to
                listOf(
                    OuterEmotion("Appalled", "Disappointed"),
                    OuterEmotion("Revolted", "Disappointed"),
                ),
            "Awful" to listOf(OuterEmotion("Nauseated", "Awful"), OuterEmotion("Detestable", "Awful")),
            "Repelled" to listOf(OuterEmotion("Horrified", "Repelled"), OuterEmotion("Hesitant", "Repelled")),
            // SURPRISED children
            "Startled" to listOf(OuterEmotion("Shocked", "Startled"), OuterEmotion("Dismayed", "Startled")),
            "Confused" to listOf(OuterEmotion("Disillusioned", "Confused"), OuterEmotion("Perplexed", "Confused")),
            "Amazed" to listOf(OuterEmotion("Astonished", "Amazed"), OuterEmotion("Awe", "Amazed")),
            "Excited" to listOf(OuterEmotion("Eager", "Excited"), OuterEmotion("Energetic", "Excited")),
            // BAD children
            "Bored" to listOf(OuterEmotion("Indifferent", "Bored"), OuterEmotion("Apathetic", "Bored")),
            "Busy" to listOf(OuterEmotion("Pressured", "Busy"), OuterEmotion("Rushed", "Busy")),
            "Stressed" to listOf(OuterEmotion("Overwhelmed", "Stressed"), OuterEmotion("Out of Control", "Stressed")),
            "Tired" to listOf(OuterEmotion("Sleepy", "Tired"), OuterEmotion("Unfocused", "Tired")),
        )

    fun buildSegments(): List<EmotionSegment> {
        val segments = mutableListOf<EmotionSegment>()
        val coreSweep = 360f / CoreEmotion.entries.size

        CoreEmotion.entries.forEachIndexed { coreIndex, core ->
            val coreStart = coreIndex * coreSweep

            // Core segment
            segments.add(
                EmotionSegment(
                    id = "core_${core.name}",
                    label = core.label,
                    layer = WheelLayer.CORE,
                    coreEmotion = core,
                    startAngle = coreStart,
                    sweepAngle = coreSweep,
                    color = core.coreColor,
                    useDarkText = core.useDarkText,
                ),
            )

            // Middle segments
            val midList = middleEmotions[core] ?: emptyList()
            val midSweep = coreSweep / midList.size

            midList.forEachIndexed { midIndex, mid ->
                val midStart = coreStart + midIndex * midSweep

                segments.add(
                    EmotionSegment(
                        id = "mid_${mid.label.replace(" ", "_")}",
                        label = mid.label,
                        layer = WheelLayer.MIDDLE,
                        coreEmotion = core,
                        startAngle = midStart,
                        sweepAngle = midSweep,
                        color = core.middleColor,
                        useDarkText = core.useDarkText,
                    ),
                )

                // Outer segments
                val outerList = outerEmotions[mid.label] ?: emptyList()
                val outerSweep = midSweep / outerList.size

                outerList.forEachIndexed { outerIndex, outer ->
                    val outerStart = midStart + outerIndex * outerSweep

                    segments.add(
                        EmotionSegment(
                            id = "outer_${outer.label.replace(" ", "_")}",
                            label = outer.label,
                            layer = WheelLayer.OUTER,
                            coreEmotion = core,
                            startAngle = outerStart,
                            sweepAngle = outerSweep,
                            color = core.outerColor,
                            useDarkText = core.useDarkText,
                        ),
                    )
                }
            }
        }

        return segments
    }
}
