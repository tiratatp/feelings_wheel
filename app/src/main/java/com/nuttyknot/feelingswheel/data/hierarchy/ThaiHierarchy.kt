package com.nuttyknot.feelingswheel.data.hierarchy

import com.nuttyknot.feelingswheel.data.model.CoreEmotion
import com.nuttyknot.feelingswheel.data.model.EmotionHierarchy
import com.nuttyknot.feelingswheel.data.model.MiddleEmotionDef
import com.nuttyknot.feelingswheel.data.model.OuterEmotionDef

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
        coreDescriptions =
            mapOf(
                CoreEmotion.HAPPY to "สภาวะที่รู้สึกดีและพึงพอใจ",
                CoreEmotion.SAD to "สภาวะที่รู้สึกไม่มีความสุขหรือเศร้าโศก",
                CoreEmotion.ANGRY to "ความรู้สึกไม่พอใจหรือเป็นปฏิปักษ์อย่างรุนแรง",
                CoreEmotion.FEARFUL to "สภาวะที่รู้สึกหวาดกลัวหรือวิตกกังวล",
                CoreEmotion.DISGUSTED to "ความรู้สึกรังเกียจหรือขยะแขยง",
                CoreEmotion.SURPRISED to "สภาวะที่รู้สึกตกใจกับสิ่งที่ไม่คาดคิด",
                CoreEmotion.BAD to "สภาวะที่รู้สึกไม่สบายใจหรืออึดอัด",
            ),
        middleEmotions =
            mapOf(
                CoreEmotion.HAPPY to
                    listOf(
                        MiddleEmotionDef(
                            label = "ร่าเริง",
                            description = "รู้สึกสนุกสนานและเบิกบาน",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("กระตือรือร้น", "รู้สึกตื่นเต้นและมีชีวิตชีวา"),
                                    OuterEmotionDef("เขิน", "รู้สึกกล้าๆ กลัวๆ อย่างน่ารัก"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "สงบ",
                            description = "รู้สึกสบายใจและผ่อนคลาย",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("ผ่อนคลาย", "รู้สึกปลอดโปร่งไร้ความกังวล"),
                                    OuterEmotionDef("ชื่นใจ", "รู้สึกสดชื่นและเบิกบาน"),
                                    OuterEmotionDef("อุ่นใจ", "รู้สึกมั่นคงและปลอดภัย"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "สนใจ",
                            description = "รู้สึกอยากเรียนรู้และใส่ใจ",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("อยากรู้", "รู้สึกกระหายที่จะเรียนรู้"),
                                    OuterEmotionDef("ใฝ่รู้", "รู้สึกอยากค้นหาและสำรวจ"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "ภูมิใจ",
                            description = "รู้สึกพอใจกับความสำเร็จของตัวเอง",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("มั่นใจ", "รู้สึกเชื่อมั่นในตัวเอง"),
                                    OuterEmotionDef("สะใจ", "รู้สึกสมหวังและปลาบปลื้ม"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "เป็นที่ยอมรับ",
                            description = "รู้สึกว่าได้รับการต้อนรับ",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("ได้รับความเคารพ", "รู้สึกว่ามีคนนับถือ"),
                                    OuterEmotionDef("ซึ้ง", "รู้สึกประทับใจอย่างลึกซึ้ง"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "มีพลัง",
                            description = "รู้สึกแข็งแกร่งและทรงอิทธิพล",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("กล้าหาญ", "รู้สึกกล้าเผชิญกับความยากลำบาก"),
                                    OuterEmotionDef("สร้างสรรค์", "รู้สึกมีแรงบันดาลใจในการสร้างสิ่งใหม่"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "สงบสุข",
                            description = "รู้สึกสงบและเป็นสุข",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("เปี่ยมรัก", "รู้สึกมีความรักและความอบอุ่น"),
                                    OuterEmotionDef("สำนึกบุญคุณ", "รู้สึกขอบคุณและซาบซึ้ง"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "ไว้วางใจ",
                            description = "รู้สึกปลอดภัยที่จะพึ่งพาผู้อื่น",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("อ่อนไหว", "รู้สึกไวต่ออารมณ์และสิ่งรอบข้าง"),
                                    OuterEmotionDef("สนิทใจ", "รู้สึกใกล้ชิดและเปิดใจ"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "มองโลกในแง่ดี",
                            description = "รู้สึกมีความหวังเกี่ยวกับอนาคต",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("มีความหวัง", "รู้สึกคาดหวังในสิ่งดีๆ"),
                                    OuterEmotionDef("ได้แรงบันดาลใจ", "รู้สึกมีแรงจูงใจที่จะลงมือทำ"),
                                ),
                        ),
                    ),
                CoreEmotion.SAD to
                    listOf(
                        MiddleEmotionDef(
                            label = "เหงา",
                            description = "รู้สึกโดดเดี่ยวและขาดเพื่อน",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("โดดเดี่ยว", "รู้สึกถูกตัดขาดจากคนอื่น"),
                                    OuterEmotionDef("ถูกทอดทิ้ง", "รู้สึกถูกทิ้งไว้ข้างหลัง"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "เปราะบาง",
                            description = "รู้สึกเปิดเผยและไม่ได้รับการปกป้อง",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("ถูกกระทำ", "รู้สึกถูกปฏิบัติอย่างไม่เป็นธรรม"),
                                    OuterEmotionDef("บอบบาง", "รู้สึกเปราะบางและแตกหักง่าย"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "สิ้นหวัง",
                            description = "รู้สึกหมดความหวังโดยสิ้นเชิง",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("เศร้าโศก", "ความเศร้าลึกซึ้งจากการสูญเสีย"),
                                    OuterEmotionDef("หมดแรง", "รู้สึกไม่สามารถเปลี่ยนแปลงอะไรได้"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "รู้สึกผิด",
                            description = "รู้สึกว่าตัวเองทำผิด",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("อับอาย", "รู้สึกเสื่อมเสียหรือขายหน้า"),
                                    OuterEmotionDef("สำนึกผิด", "รู้สึกเสียใจอย่างมากกับการกระทำ"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "หดหู่",
                            description = "รู้สึกหม่นหมองและไร้ความหวัง",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("ด้อยค่า", "รู้สึกว่าตัวเองมีคุณค่าน้อยกว่าคนอื่น"),
                                    OuterEmotionDef("ว่างเปล่า", "รู้สึกกลวงเปล่าไร้อารมณ์"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "ผิดหวัง",
                            description = "รู้สึกเสียใจที่สิ่งที่หวังไม่เป็นจริง",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("เสียใจ", "รู้สึกโศกเศร้ากับสิ่งที่เกิดขึ้น"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "เจ็บปวด",
                            description = "รู้สึกเจ็บปวดทางอารมณ์",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("ทรมาน", "รู้สึกทุกข์ทรมานอย่างรุนแรง"),
                                    OuterEmotionDef("น้อยใจ", "รู้สึกว่าไม่ได้รับความใส่ใจ"),
                                ),
                        ),
                    ),
                CoreEmotion.ANGRY to
                    listOf(
                        MiddleEmotionDef(
                            label = "ถูกทำให้ผิดหวัง",
                            description = "รู้สึกถูกทำให้ผิดหวังจากคนที่ไว้ใจ",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("ถูกหักหลัง", "รู้สึกว่าความไว้ใจถูกทำลาย"),
                                    OuterEmotionDef("เจ็บแค้น", "รู้สึกขมขื่นกับการถูกปฏิบัติอย่างไม่เป็นธรรม"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "อัปยศ",
                            description = "รู้สึกถูกทำให้อับอายต่อหน้าสาธารณะ",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("ถูกดูหมิ่น", "รู้สึกถูกปฏิบัติอย่างไม่ให้เกียรติ"),
                                    OuterEmotionDef("ถูกเยาะเย้ย", "รู้สึกถูกล้อเลียนหรือเหยียดหยาม"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "ขมขื่น",
                            description = "รู้สึกเจ็บแค้นและแข็งกระด้าง",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("ขุ่นเคือง", "รู้สึกโกรธกับความไม่ยุติธรรม"),
                                    OuterEmotionDef("ถูกล่วงล้ำ", "รู้สึกว่าขอบเขตถูกรุกล้ำ"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "โมโห",
                            description = "รู้สึกโกรธอย่างรุนแรง",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("เกรี้ยวกราด", "รู้สึกโกรธจัดอย่างควบคุมไม่ได้"),
                                    OuterEmotionDef("อิจฉา", "รู้สึกอยากได้ในสิ่งที่คนอื่นมี"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "ก้าวร้าว",
                            description = "รู้สึกเป็นปฏิปักษ์และต้องการต่อสู้",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("ถูกยั่วยุ", "รู้สึกถูกกระตุ้นให้โกรธโดยเจตนา"),
                                    OuterEmotionDef("เป็นปฏิปักษ์", "รู้สึกไม่เป็นมิตรและเป็นศัตรู"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "หงุดหงิด",
                            description = "รู้สึกถูกขัดขวางจากเป้าหมาย",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("รำคาญ", "รู้สึกรำคาญและหงุดหงิดเล็กน้อย"),
                                    OuterEmotionDef("งอน", "รู้สึกน้อยใจและไม่พอใจ"),
                                    OuterEmotionDef("หมั่นไส้", "รู้สึกขัดใจและไม่ชอบ"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "ห่างเหิน",
                            description = "รู้สึกแยกตัวทางอารมณ์",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("เก็บตัว", "ถอยห่างจากปฏิสัมพันธ์กับคนอื่น"),
                                    OuterEmotionDef("ชาชิน", "รู้สึกไม่สามารถรู้สึกอะไรได้"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "จับผิด",
                            description = "รู้สึกชอบตัดสินและหาข้อตำหนิ",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("ระแวง", "รู้สึกสงสัยและไม่ไว้วางใจ"),
                                    OuterEmotionDef("เมินเฉย", "รู้สึกไม่สนใจและมองข้ามผู้อื่น"),
                                ),
                        ),
                    ),
                CoreEmotion.FEARFUL to
                    listOf(
                        MiddleEmotionDef(
                            label = "ขวัญหาย",
                            description = "รู้สึกหวาดกลัวและตื่นตระหนก",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("สิ้นหนทาง", "รู้สึกไม่สามารถช่วยเหลือตัวเองได้"),
                                    OuterEmotionDef("หวาดผวา", "รู้สึกกลัวอย่างฉับพลัน"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "กังวล",
                            description = "รู้สึกกระวนกระวายและเป็นห่วง",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("รับไม่ไหว", "รู้สึกมีภาระมากเกินไป"),
                                    OuterEmotionDef("เกรงใจ", "รู้สึกเกรงว่าจะรบกวนผู้อื่น"),
                                    OuterEmotionDef("อึดอัด", "รู้สึกอัดอั้นและไม่สบายใจ"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "ไม่มั่นคง",
                            description = "รู้สึกไม่แน่ใจในตัวเอง",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("รู้สึกไม่ดีพอ", "รู้สึกว่าตัวเองยังไม่เพียงพอ"),
                                    OuterEmotionDef("ด้อยกว่า", "รู้สึกว่าตัวเองต่ำกว่าคนอื่น"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "อ่อนแอ",
                            description = "รู้สึกขาดกำลังหรือความมุ่งมั่น",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("ไร้ค่า", "รู้สึกว่าตัวเองไม่มีคุณค่า"),
                                    OuterEmotionDef("ไม่สำคัญ", "รู้สึกว่าตัวเองเล็กน้อยเกินไป"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "ถูกปฏิเสธ",
                            description = "รู้สึกถูกผลักไสหรือไม่เป็นที่ต้องการ",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("ถูกกีดกัน", "รู้สึกถูกกันออกโดยเจตนา"),
                                    OuterEmotionDef("ถูกข่มเหง", "รู้สึกถูกเล่นงานอย่างไม่เป็นธรรม"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "หวาดหวั่น",
                            description = "รู้สึกว่าอยู่ในอันตราย",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("ตื่นตระหนก", "รู้สึกเครียดและตึงเครียด"),
                                    OuterEmotionDef("เกรงกลัว", "รู้สึกไม่ได้รับการปกป้อง"),
                                ),
                        ),
                    ),
                CoreEmotion.SURPRISED to
                    listOf(
                        MiddleEmotionDef(
                            label = "ตกใจ",
                            description = "รู้สึกตกใจกะทันหัน",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("อึ้ง", "รู้สึกตกตะลึงอย่างมาก"),
                                    OuterEmotionDef("ใจหาย", "รู้สึกหวาดหวั่นกับสิ่งที่ไม่คาดคิด"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "สับสน",
                            description = "รู้สึกงุนงงและไม่ชัดเจน",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("สิ้นศรัทธา", "รู้สึกผิดหวังกับสิ่งที่เคยเชื่อ"),
                                    OuterEmotionDef("งุนงง", "รู้สึกสับสนและไม่เข้าใจ"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "ทึ่ง",
                            description = "รู้สึกอัศจรรย์ใจและตะลึง",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("อัศจรรย์ใจ", "รู้สึกประทับใจอย่างยิ่ง"),
                                    OuterEmotionDef("เกรงขาม", "รู้สึกเคารพและยำเกรง"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "ตื่นเต้น",
                            description = "รู้สึกกระตือรือร้นและใจจดใจจ่อ",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("ใจจดใจจ่อ", "รู้สึกพร้อมและอยากเริ่มต้น"),
                                    OuterEmotionDef("เปี่ยมพลัง", "รู้สึกเต็มไปด้วยพลังและชีวิตชีวา"),
                                ),
                        ),
                    ),
                CoreEmotion.DISGUSTED to
                    listOf(
                        MiddleEmotionDef(
                            label = "ไม่เห็นด้วย",
                            description = "รู้สึกว่าสิ่งนั้นผิดหรือไม่เหมาะสม",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("ดูถูก", "ตัดสินผู้อื่นอย่างรุนแรง"),
                                    OuterEmotionDef("ขายหน้า", "รู้สึกอึดอัดและเสียหน้า"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "สลดใจ",
                            description = "รู้สึกผิดหวังกับสิ่งที่เกิดขึ้น",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("ตกตะลึง", "รู้สึกตกใจและสยดสยอง"),
                                    OuterEmotionDef("สะอิดสะเอียน", "รู้สึกรังเกียจอย่างรุนแรง"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "เลวร้าย",
                            description = "รู้สึกแย่มากหรือไม่น่าพอใจอย่างยิ่ง",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("คลื่นไส้", "รู้สึกป่วยไข้ทางกาย"),
                                    OuterEmotionDef("น่าชิงชัง", "รู้สึกว่าสิ่งนั้นสมควรถูกเกลียดชัง"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "ขยะแขยง",
                            description = "รู้สึกถูกผลักไสด้วยความรังเกียจ",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("สยดสยอง", "รู้สึกตกใจและหวาดกลัวอย่างมาก"),
                                    OuterEmotionDef("ลังเล", "รู้สึกไม่แน่ใจและลังเลใจ"),
                                ),
                        ),
                    ),
                CoreEmotion.BAD to
                    listOf(
                        MiddleEmotionDef(
                            label = "เบื่อ",
                            description = "รู้สึกไม่สนใจและกระสับกระส่าย",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("เฉยๆ", "รู้สึกไม่สนใจหรือใส่ใจ"),
                                    OuterEmotionDef("เซ็ง", "รู้สึกขาดแรงจูงใจ"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "ยุ่ง",
                            description = "รู้สึกมีภาระมากเกินไป",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("ถูกเร่ง", "รู้สึกถูกกดดันให้ทำให้เสร็จ"),
                                    OuterEmotionDef("รีบร้อน", "รู้สึกเร่งรีบและไม่มีเวลา"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "เครียด",
                            description = "รู้สึกกดดันทางจิตใจหรืออารมณ์",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("กดดัน", "รู้สึกไม่สามารถรับมือกับสิ่งต่างๆ ได้"),
                                    OuterEmotionDef("ควบคุมไม่ได้", "รู้สึกว่าสิ่งต่างๆ อยู่เหนือการจัดการ"),
                                ),
                        ),
                        MiddleEmotionDef(
                            label = "เหนื่อย",
                            description = "รู้สึกหมดแรงและต้องการพักผ่อน",
                            outerEmotions =
                                listOf(
                                    OuterEmotionDef("ท้อ", "รู้สึกง่วงและอยากหลับ"),
                                    OuterEmotionDef("หมอง", "รู้สึกกระจัดกระจายและไม่มีสมาธิ"),
                                ),
                        ),
                    ),
            ),
    )
