package com.nuttyknot.feelingswheel.data.hierarchy

import com.nuttyknot.feelingswheel.data.model.EmotionHierarchy
import com.nuttyknot.feelingswheel.data.model.SupportedLanguage

object HierarchyProvider {
    fun hierarchyFor(language: SupportedLanguage): EmotionHierarchy =
        when (language) {
            SupportedLanguage.ENGLISH -> englishHierarchy()
            SupportedLanguage.THAI -> thaiHierarchy()
        }
}
