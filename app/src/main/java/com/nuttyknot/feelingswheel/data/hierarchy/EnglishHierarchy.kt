package com.nuttyknot.feelingswheel.data.hierarchy

import com.nuttyknot.feelingswheel.data.model.CoreEmotion
import com.nuttyknot.feelingswheel.data.model.EmotionHierarchy
import com.nuttyknot.feelingswheel.data.model.MiddleEmotionDef
import com.nuttyknot.feelingswheel.data.model.OuterEmotionDef

@Suppress("LongMethod")
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
        coreDescriptions =
            mapOf(
                CoreEmotion.HAPPY to "A state of well-being and contentment",
                CoreEmotion.SAD to "A state of unhappiness or sorrow",
                CoreEmotion.ANGRY to "A strong feeling of displeasure or hostility",
                CoreEmotion.FEARFUL to "A state of being afraid or anxious",
                CoreEmotion.DISGUSTED to "A strong feeling of dislike or revulsion",
                CoreEmotion.SURPRISED to "A state of being struck by something unexpected",
                CoreEmotion.BAD to "A general state of discomfort or unease",
            ),
        middleEmotions =
            mapOf(
                CoreEmotion.HAPPY to
                    listOf(
                        MiddleEmotionDef(
                            label = "Playful",
                            description = "Feeling light-hearted and fun",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Aroused", "Feeling stimulated and excited"),
                                    OuterEmotionDef("Cheeky", "Feeling mischievous and bold"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Content",
                            description = "Feeling satisfied and at ease",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Free", "Feeling unrestricted and liberated"),
                                    OuterEmotionDef("Joyful", "Feeling great delight and happiness"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Interested",
                            description = "Feeling engaged and attentive",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Curious", "Eager to learn or know more"),
                                    OuterEmotionDef("Inquisitive", "Having a desire to investigate"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Proud",
                            description = "Feeling pleased by one's achievements",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Successful", "Feeling accomplished and effective"),
                                    OuterEmotionDef("Confident", "Feeling self-assured and capable"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Accepted",
                            description = "Feeling welcomed and included",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Respected", "Feeling admired and esteemed"),
                                    OuterEmotionDef("Valued", "Feeling important and appreciated"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Powerful",
                            description = "Feeling strong and influential",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Courageous", "Feeling brave in the face of difficulty"),
                                    OuterEmotionDef("Creative", "Feeling inspired to make or imagine"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Peaceful",
                            description = "Feeling calm and serene",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Loving", "Feeling deep affection and warmth"),
                                    OuterEmotionDef("Thankful", "Feeling grateful and appreciative"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Trusting",
                            description = "Feeling safe to rely on others",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Sensitive", "Feeling deeply aware and responsive"),
                                    OuterEmotionDef("Intimate", "Feeling closely connected and open"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Optimistic",
                            description = "Feeling hopeful about the future",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Hopeful", "Feeling expectant of positive outcomes"),
                                    OuterEmotionDef("Inspired", "Feeling mentally stimulated to act"),
                                ),
                        ),
                    ),
                CoreEmotion.SAD to
                    listOf(
                        MiddleEmotionDef(
                            label = "Lonely",
                            description = "Feeling isolated and without companionship",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Isolated", "Feeling cut off from others"),
                                    OuterEmotionDef("Abandoned", "Feeling left behind or deserted"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Vulnerable",
                            description = "Feeling exposed and unprotected",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Victimized", "Feeling unfairly treated or harmed"),
                                    OuterEmotionDef("Fragile", "Feeling delicate and easily broken"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Despair",
                            description = "Feeling a complete loss of hope",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Grief", "Deep sorrow from loss"),
                                    OuterEmotionDef("Powerless", "Feeling unable to change anything"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Guilty",
                            description = "Feeling responsible for a wrongdoing",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Ashamed", "Feeling disgraced or humiliated"),
                                    OuterEmotionDef("Remorseful", "Feeling deep regret for one's actions"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Depressed",
                            description = "Feeling persistently low and hopeless",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Inferior", "Feeling less worthy than others"),
                                    OuterEmotionDef("Empty", "Feeling hollow and devoid of emotion"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Hurt",
                            description = "Feeling emotionally wounded",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Embarrassed", "Feeling self-conscious and awkward"),
                                    OuterEmotionDef("Disappointed", "Feeling let down by unmet expectations"),
                                ),
                        ),
                    ),
                CoreEmotion.ANGRY to
                    listOf(
                        MiddleEmotionDef(
                            label = "Let Down",
                            description = "Feeling failed by someone you trusted",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Betrayed", "Feeling trust has been broken"),
                                    OuterEmotionDef("Resentful", "Feeling bitter about unfair treatment"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Humiliated",
                            description = "Feeling publicly shamed or degraded",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Disrespected", "Feeling treated without regard"),
                                    OuterEmotionDef("Ridiculed", "Feeling mocked or made fun of"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Bitter",
                            description = "Feeling resentful and hardened",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Indignant", "Feeling anger at perceived injustice"),
                                    OuterEmotionDef("Violated", "Feeling one's boundaries were crossed"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Mad",
                            description = "Feeling intensely angry",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Furious", "Feeling extreme, uncontrollable anger"),
                                    OuterEmotionDef("Jealous", "Feeling envious of what others have"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Aggressive",
                            description = "Feeling hostile and combative",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Provoked", "Feeling deliberately irritated"),
                                    OuterEmotionDef("Hostile", "Feeling unfriendly and antagonistic"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Frustrated",
                            description = "Feeling blocked from achieving a goal",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Infuriated", "Feeling extremely frustrated and angry"),
                                    OuterEmotionDef("Annoyed", "Feeling mildly irritated or bothered"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Distant",
                            description = "Feeling emotionally detached",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Withdrawn", "Pulling back from interaction"),
                                    OuterEmotionDef("Numb", "Feeling unable to experience emotions"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Critical",
                            description = "Feeling judgmental and fault-finding",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Skeptical", "Feeling doubtful and questioning"),
                                    OuterEmotionDef("Dismissive", "Feeling inclined to disregard others"),
                                ),
                        ),
                    ),
                CoreEmotion.FEARFUL to
                    listOf(
                        MiddleEmotionDef(
                            label = "Scared",
                            description = "Feeling frightened and alarmed",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Helpless", "Feeling unable to help oneself"),
                                    OuterEmotionDef("Frightened", "Feeling sudden fear or shock"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Anxious",
                            description = "Feeling uneasy and worried",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Overwhelmed", "Feeling buried by too much at once"),
                                    OuterEmotionDef("Worried", "Feeling troubled about potential problems"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Insecure",
                            description = "Feeling uncertain about oneself",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Inadequate", "Feeling not good enough"),
                                    OuterEmotionDef("Inferior", "Feeling lower in status or ability"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Weak",
                            description = "Feeling lacking in strength or resolve",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Worthless", "Feeling having no value"),
                                    OuterEmotionDef("Insignificant", "Feeling too small to matter"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Rejected",
                            description = "Feeling pushed away or unwanted",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Excluded", "Feeling deliberately left out"),
                                    OuterEmotionDef("Persecuted", "Feeling unfairly targeted or harassed"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Threatened",
                            description = "Feeling in danger or at risk",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Nervous", "Feeling tense and on edge"),
                                    OuterEmotionDef("Exposed", "Feeling unprotected and visible"),
                                ),
                        ),
                    ),
                CoreEmotion.DISGUSTED to
                    listOf(
                        MiddleEmotionDef(
                            label = "Disapproving",
                            description = "Feeling that something is wrong or unacceptable",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Judgmental", "Forming harsh opinions of others"),
                                    OuterEmotionDef("Embarrassed", "Feeling uncomfortable and self-conscious"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Disappointed",
                            description = "Feeling let down by what happened",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Appalled", "Feeling shocked and horrified"),
                                    OuterEmotionDef("Revolted", "Feeling intense disgust"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Awful",
                            description = "Feeling extremely bad or unpleasant",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Nauseated", "Feeling physically sickened"),
                                    OuterEmotionDef("Detestable", "Feeling something deserves hatred"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Repelled",
                            description = "Feeling driven away by strong aversion",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Horrified", "Feeling intense shock and dread"),
                                    OuterEmotionDef("Hesitant", "Feeling reluctant and unsure"),
                                ),
                        ),
                    ),
                CoreEmotion.SURPRISED to
                    listOf(
                        MiddleEmotionDef(
                            label = "Startled",
                            description = "Feeling suddenly alarmed or taken aback",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Shocked", "Feeling deeply stunned or jarred"),
                                    OuterEmotionDef("Dismayed", "Feeling distressed by something unexpected"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Confused",
                            description = "Feeling bewildered and unclear",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Disillusioned", "Feeling let down by a false belief"),
                                    OuterEmotionDef("Perplexed", "Feeling puzzled and unable to understand"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Amazed",
                            description = "Feeling great wonder and astonishment",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Astonished", "Feeling greatly impressed or surprised"),
                                    OuterEmotionDef("Awe", "Feeling reverence and wonder"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Excited",
                            description = "Feeling enthusiastic and eager",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Eager", "Feeling keen and ready to begin"),
                                    OuterEmotionDef("Energetic", "Feeling full of vitality and drive"),
                                ),
                        ),
                    ),
                CoreEmotion.BAD to
                    listOf(
                        MiddleEmotionDef(
                            label = "Bored",
                            description = "Feeling uninterested and restless",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Indifferent", "Feeling no interest or concern"),
                                    OuterEmotionDef("Apathetic", "Feeling a lack of motivation or care"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Busy",
                            description = "Feeling occupied with too many demands",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Pressured", "Feeling pushed to perform or deliver"),
                                    OuterEmotionDef("Rushed", "Feeling hurried and short on time"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Stressed",
                            description = "Feeling under mental or emotional strain",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Overwhelmed", "Feeling unable to cope with demands"),
                                    OuterEmotionDef("Out of Control", "Feeling things are beyond management"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "Tired",
                            description = "Feeling drained and in need of rest",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("Sleepy", "Feeling drowsy and ready to sleep"),
                                    OuterEmotionDef("Unfocused", "Feeling scattered and unable to concentrate"),
                                ),
                        ),
                    ),
            ),
    )
