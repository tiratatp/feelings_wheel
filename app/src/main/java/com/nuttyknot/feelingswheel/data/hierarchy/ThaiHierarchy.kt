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
                            "สนใจ",
                            listOf("อยากรู้", "ใฝ่รู้"),
                        ),
                        MiddleEmotionDef(
                            "ภูมิใจ",
                            listOf("มั่นใจ", "สะใจ"),
                        ),
                        MiddleEmotionDef(
                            "เป็นที่ยอมรับ",
                            listOf("ได้รับความเคารพ", "ซึ้ง"),
                        ),
                        MiddleEmotionDef(
                            "มีพลัง",
                            listOf("กล้าหาญ", "สร้างสรรค์"),
                        ),
                        MiddleEmotionDef(
                            "สงบสุข",
                            listOf("เปี่ยมรัก", "สำนึกบุญคุณ"),
                        ),
                        MiddleEmotionDef(
                            "ไว้วางใจ",
                            listOf("อ่อนไหว", "สนิทใจ"),
                        ),
                        MiddleEmotionDef(
                            "มองโลกในแง่ดี",
                            listOf("มีความหวัง", "ได้แรงบันดาลใจ"),
                        ),
                    ),
                CoreEmotion.SAD to
                    listOf(
                        MiddleEmotionDef(
                            "เหงา",
                            listOf("โดดเดี่ยว", "ถูกทอดทิ้ง"),
                        ),
                        MiddleEmotionDef(
                            "เปราะบาง",
                            listOf("ถูกกระทำ", "บอบบาง"),
                        ),
                        MiddleEmotionDef(
                            "สิ้นหวัง",
                            listOf("เศร้าโศก", "หมดแรง"),
                        ),
                        MiddleEmotionDef(
                            "รู้สึกผิด",
                            listOf("อับอาย", "สำนึกผิด"),
                        ),
                        MiddleEmotionDef(
                            "หดหู่",
                            listOf("ด้อยค่า", "ว่างเปล่า"),
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
                            "ถูกทำให้ผิดหวัง",
                            listOf("ถูกหักหลัง", "เจ็บแค้น"),
                        ),
                        MiddleEmotionDef(
                            "อัปยศ",
                            listOf("ถูกดูหมิ่น", "ถูกเยาะเย้ย"),
                        ),
                        MiddleEmotionDef(
                            "ขมขื่น",
                            listOf("ขุ่นเคือง", "ถูกล่วงล้ำ"),
                        ),
                        MiddleEmotionDef(
                            "โมโห",
                            listOf("เกรี้ยวกราด", "อิจฉา"),
                        ),
                        MiddleEmotionDef(
                            "ก้าวร้าว",
                            listOf("ถูกยั่วยุ", "เป็นปฏิปักษ์"),
                        ),
                        MiddleEmotionDef(
                            "หงุดหงิด",
                            listOf("รำคาญ", "งอน", "หมั่นไส้"),
                        ),
                        MiddleEmotionDef(
                            "ห่างเหิน",
                            listOf("เก็บตัว", "ชาชิน"),
                        ),
                        MiddleEmotionDef(
                            "จับผิด",
                            listOf("ระแวง", "เมินเฉย"),
                        ),
                    ),
                CoreEmotion.FEARFUL to
                    listOf(
                        MiddleEmotionDef(
                            "ขวัญหาย",
                            listOf("สิ้นหนทาง", "หวาดผวา"),
                        ),
                        MiddleEmotionDef(
                            "กังวล",
                            listOf("รับไม่ไหว", "เกรงใจ", "อึดอัด"),
                        ),
                        MiddleEmotionDef(
                            "ไม่มั่นคง",
                            listOf("รู้สึกไม่ดีพอ", "ด้อยกว่า"),
                        ),
                        MiddleEmotionDef(
                            "อ่อนแอ",
                            listOf("ไร้ค่า", "ไม่สำคัญ"),
                        ),
                        MiddleEmotionDef(
                            "ถูกปฏิเสธ",
                            listOf("ถูกกีดกัน", "ถูกข่มเหง"),
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
                            listOf("สิ้นศรัทธา", "งุนงง"),
                        ),
                        MiddleEmotionDef(
                            "ทึ่ง",
                            listOf("อัศจรรย์ใจ", "เกรงขาม"),
                        ),
                        MiddleEmotionDef(
                            "ตื่นเต้น",
                            listOf("ใจจดใจจ่อ", "เปี่ยมพลัง"),
                        ),
                    ),
                CoreEmotion.DISGUSTED to
                    listOf(
                        MiddleEmotionDef(
                            "ไม่เห็นด้วย",
                            listOf("ดูถูก", "ขายหน้า"),
                        ),
                        MiddleEmotionDef(
                            "สลดใจ",
                            listOf("ตกตะลึง", "สะอิดสะเอียน"),
                        ),
                        MiddleEmotionDef(
                            "เลวร้าย",
                            listOf("คลื่นไส้", "น่าชิงชัง"),
                        ),
                        MiddleEmotionDef(
                            "ขยะแขยง",
                            listOf("สยดสยอง", "ลังเล"),
                        ),
                    ),
                CoreEmotion.BAD to
                    listOf(
                        MiddleEmotionDef(
                            "เบื่อ",
                            listOf("เฉยๆ", "เซ็ง"),
                        ),
                        MiddleEmotionDef(
                            "ยุ่ง",
                            listOf("ถูกเร่ง", "รีบร้อน"),
                        ),
                        MiddleEmotionDef(
                            "เครียด",
                            listOf("กดดัน", "ควบคุมไม่ได้"),
                        ),
                        MiddleEmotionDef(
                            "เหนื่อย",
                            listOf("ท้อ", "หมอง"),
                        ),
                    ),
            ),
    )
