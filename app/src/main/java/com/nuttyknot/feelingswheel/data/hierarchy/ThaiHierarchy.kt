package com.nuttyknot.feelingswheel.data.hierarchy

import com.nuttyknot.feelingswheel.data.model.CoreEmotion
import com.nuttyknot.feelingswheel.data.model.EmotionHierarchy
import com.nuttyknot.feelingswheel.data.model.MiddleEmotionDef

@Suppress("LongMethod")
fun thaiHierarchy(): EmotionHierarchy =
    EmotionHierarchy(
        coreLabels =
            mapOf(
                CoreEmotion.HAPPY to "มีความสุข",
                CoreEmotion.SAD to "เศร้า",
                CoreEmotion.ANGRY to "โกรธ",
                CoreEmotion.FEARFUL to "กลัว",
                CoreEmotion.SURPRISED to "ประหลาดใจ",
                CoreEmotion.DISGUSTED to "รังเกียจ",
                CoreEmotion.BAD to "แย่",
            ),
        middleEmotions =
            mapOf(
                CoreEmotion.HAPPY to
                    listOf(
                        MiddleEmotionDef(
                            "ร่าเริง",
                            listOf("กระตือรือร้น", "เขิน"),
                        ),
                        MiddleEmotionDef(
                            "สงบ",
                            listOf("ผ่อนคลาย", "ชื่นใจ", "อุ่นใจ"),
                        ),
                        MiddleEmotionDef(
                            "ภูมิใจ",
                            listOf("มั่นใจ", "สะใจ"),
                        ),
                        MiddleEmotionDef(
                            "เป็นที่ยอมรับ",
                            listOf("ได้รับความเคารพ", "ซึ้ง"),
                        ),
                    ),
                CoreEmotion.SAD to
                    listOf(
                        MiddleEmotionDef(
                            "เหงา",
                            listOf("โดดเดี่ยว"),
                        ),
                        MiddleEmotionDef(
                            "หดหู่",
                            listOf("ว่างเปล่า"),
                        ),
                        MiddleEmotionDef(
                            "ผิดหวัง",
                            listOf("เสียใจ"),
                        ),
                        MiddleEmotionDef(
                            "เจ็บปวด",
                            listOf("ทรมาน", "น้อยใจ"),
                        ),
                    ),
                CoreEmotion.ANGRY to
                    listOf(
                        MiddleEmotionDef(
                            "หงุดหงิด",
                            listOf("รำคาญ", "งอน", "หมั่นไส้"),
                        ),
                        MiddleEmotionDef(
                            "โมโห",
                            listOf("เกรี้ยวกราด"),
                        ),
                        MiddleEmotionDef(
                            "ขมขื่น",
                            listOf("ขุ่นเคือง"),
                        ),
                    ),
                CoreEmotion.FEARFUL to
                    listOf(
                        MiddleEmotionDef(
                            "กังวล",
                            listOf("รับไม่ไหว", "เกรงใจ", "อึดอัด"),
                        ),
                        MiddleEmotionDef(
                            "ไม่มั่นคง",
                            listOf("รู้สึกไม่ดีพอ"),
                        ),
                        MiddleEmotionDef(
                            "หวาดหวั่น",
                            listOf("ตื่นตระหนก", "เกรงกลัว"),
                        ),
                    ),
                CoreEmotion.SURPRISED to
                    listOf(
                        MiddleEmotionDef(
                            "ตกใจ",
                            listOf("อึ้ง", "ใจหาย"),
                        ),
                        MiddleEmotionDef(
                            "สับสน",
                            listOf("งุนงง"),
                        ),
                        MiddleEmotionDef(
                            "ทึ่ง",
                            listOf("อัศจรรย์ใจ"),
                        ),
                    ),
                CoreEmotion.DISGUSTED to
                    listOf(
                        MiddleEmotionDef(
                            "ไม่เห็นด้วย",
                            listOf("ดูถูก"),
                        ),
                        MiddleEmotionDef(
                            "ขยะแขยง",
                            listOf("สยดสยอง"),
                        ),
                    ),
                CoreEmotion.BAD to
                    listOf(
                        MiddleEmotionDef(
                            "เบื่อ",
                            listOf("เฉยๆ", "เซ็ง"),
                        ),
                        MiddleEmotionDef(
                            "เครียด",
                            listOf("กดดัน"),
                        ),
                        MiddleEmotionDef(
                            "เหนื่อย",
                            listOf("ท้อ", "หมอง"),
                        ),
                    ),
            ),
    )
