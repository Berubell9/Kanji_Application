package com.example.kanji.ui.theme

import androidx.compose.ui.graphics.Color

// =====================================================
// App Base Colors
// =====================================================

// สีพื้นหลังหลักของแอปทั้งระบบ
val AppBackground = Color(0xFFFDE5EF)

// สีหลักของปุ่มหลัก เช่น เริ่ม / ถัดไป
val GreenPrimary = Color(0xFFFFA8B8)



// =====================================================
// Surface / Background
// =====================================================

// สีพื้นการ์ด / กล่อง
val CardWhite = Color(0xFFFFFCFB)

// สีขาวล้วน
val BgWhite = Color(0xFFFFFFFF)

// สีพื้นกล่องคำถาม / กล่องคะแนน
val QuestionBg = Color(0xFFFCE7F3)


// =====================================================
// Text Colors
// =====================================================

// สีข้อความหลัก - น้ำตาลเข้ม อ่านชัด
val TextPrimary = Color(0xFF4E342E)

// สีข้อความรอง - น้ำตาลกลาง อ่านง่าย
val TextSecondary = Color(0xFF6D4C41)

// สีหัวข้อใหญ่ เช่น เลือกหมวดหมู่ / เลือกโหมด
val CategoryTitle = Color(0xFF3E2723)

// สีหัวข้อหน้า Home / Name / Result
val HomeTitle = Color(0xFF3E2723)

// สีข้อความแสดงสิ่งที่เลือก เช่น หมวดที่เลือก / โหมดที่เลือก
val CategorySelectedText = Color(0xFF6D4C41)

// สีข้อความใน badge เช่น “เลือกแล้ว”
val SelectedBadgeText = Color(0xFF4E342E)

// สีหัวข้อหลักในหน้า Quiz
val HeaderPrimary = Color(0xFF3E2723)

// สีข้อความรองในหน้า Quiz
val HeaderSecondary = Color(0xFF5D4037)


// =====================================================
// Border / Accent
// =====================================================

// สีเส้นขอบอ่อน
val BorderSoft = Color(0xFFF3D8DE)

// สีเส้นขอบโปร่ง
val ButtonBorder = Color(0x1F000000)

// สีเน้นรอง
val AccentYellow = Color(0xFFFFD6A5)


// =====================================================
// Error / Success / Result
// =====================================================

// สีเขียวสถานะถูก
val CorrectGreen = Color(0xFF7EC8A5)

// สีพื้นหลังคำตอบถูก
val CorrectBg = Color(0xFFEAF9F1)

// สีพื้น/ข้อความสถานะถูกตอนเฉลย
val CorrectColor = Color(0xFF63C132)

// สีแดงสถานะผิด
val WrongRed = Color(0xFFFF8FA3)

// สีพื้นหลังคำตอบผิด
val WrongBg = Color(0xFFFFEEF1)

// สีพื้น/ข้อความสถานะผิดตอนเฉลย
val WrongColor = Color(0xFFFF3B5C)

// สีผิดแบบ muted
val WrongMuted = Color(0xFFA95A5A)

// สีพื้นกล่อง error
val ErrorSoftBg = Color(0xFFFFEEF1)

// สีข้อความ error
val ErrorText = Color(0xFFFF3B5C)


// =====================================================
// Choice Button Colors
// =====================================================

// ตัวเลือก A
val ChoiceRed = Color(0xFF6F2DBD)

// ตัวเลือก B
val ChoiceBlue = Color(0xFF71A5E5)

// ตัวเลือก C
val ChoiceYellow = Color(0xFFFBBF24)

// ตัวเลือก D
val ChoiceGreen = Color(0xFF8FD37C)


// =====================================================
// Progress
// =====================================================

// progress ที่ทำแล้ว
val ProgressActive = Color(0xFFFA3B87)

// progress ที่ยังไม่ทำ
val ProgressInactive = Color(0xFFFBCFE8)


// =====================================================
// Buttons
// =====================================================

// ปุ่มย้อนกลับ - พื้น
val BackButtonBg = Color(0xFFD9D2E0)

// ปุ่มย้อนกลับ - ข้อความ
val BackButtonText = Color(0xFF5D4037)

// ปุ่มรอง - พื้น
val SecondaryButtonBg = Color(0xFFFCE7F3)

// ปุ่มรอง - ข้อความ
val SecondaryButtonText = Color(0xFF4E342E)


// =====================================================
// Home Chips / Latest Result
// =====================================================

// พื้น chip ที่เลือก
val CategoryChipSelectedBg = Color(0xFFFFE3E8)

// พื้น chip ที่ยังไม่เลือก
val CategoryChipUnselectedBg = Color(0xFFFFFCFB)

// ขอบ chip ที่เลือก
val CategoryChipSelectedBorder = Color(0xFFFFA8B8)

// ขอบ chip ที่ยังไม่เลือก
val CategoryChipUnselectedBorder = Color(0xFFF3D8DE)

// พื้นรายการผลล่าสุด
val LatestResultItemBg = Color(0xFFFFE3E8)


// =====================================================
// Category Card Gradients
// =====================================================

// หมวด "ทั้งหมด"
val AllGradientStart = Color(0xFF3A185E)
val AllGradientMid = Color(0xFF6F2DBD)
val AllGradientEnd = Color(0xFF9D4EDD)

// หมวด "สัตว์"
val AnimalGradientStart = Color(0xFF1F4D2E)
val AnimalGradientMid = Color(0xFF2E8B57)
val AnimalGradientEnd = Color(0xFF57CC99)

// หมวด "สิ่งของ"
val ObjectGradientStart = Color(0xFF6B3E12)
val ObjectGradientMid = Color(0xFFD97706)
val ObjectGradientEnd = Color(0xFFFBBF24)

// หมวด "กริยา"
val VerbGradientStart = Color(0xFF7A1029)
val VerbGradientMid = Color(0xFFD6336C)
val VerbGradientEnd = Color(0xFFFF8FAB)


// =====================================================
// Mode Card Gradients
// =====================================================

// โหมดทายการอ่าน
val ModeReadingGradientStart = Color(0xFF180022)
val ModeReadingGradientMid = Color(0xFF3E0D6B)
val ModeReadingGradientEnd = Color(0xFF7A3FD3)

// โหมดทายความหมาย
val ModeMeaningGradientStart = Color(0xFF5B000A)
val ModeMeaningGradientMid = Color(0xFFA20D2A)
val ModeMeaningGradientEnd = Color(0xFFE65A7A)


// =====================================================
// Overlay / Decoration
// =====================================================

// overlay ขาวจาง
val OverlayWhiteSoft = Color(0x1AFFFFFF)

// overlay ดำจาง
val OverlayBlackSoft = Color(0x1A000000)

// overlay ดำเข้ม
val OverlayBlackStrong = Color(0x33000000)