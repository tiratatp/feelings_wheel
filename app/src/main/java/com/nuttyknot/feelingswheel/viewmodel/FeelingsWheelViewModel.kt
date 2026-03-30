package com.nuttyknot.feelingswheel.viewmodel

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nuttyknot.feelingswheel.data.EmotionData
import com.nuttyknot.feelingswheel.data.SettingsRepository
import com.nuttyknot.feelingswheel.data.hierarchy.HierarchyProvider
import com.nuttyknot.feelingswheel.data.model.EmotionHierarchy
import com.nuttyknot.feelingswheel.data.model.EmotionSegment
import com.nuttyknot.feelingswheel.data.model.SelectedEmotion
import com.nuttyknot.feelingswheel.data.model.SupportedLanguage
import com.nuttyknot.feelingswheel.data.model.WheelLayer
import com.nuttyknot.feelingswheel.data.model.WheelPalette
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class WheelUiState(
    val segments: List<EmotionSegment> = emptyList(),
    val selectedEmotion: SelectedEmotion? = null,
    val currentPalette: WheelPalette = WheelPalette.Pastel,
    val currentLanguage: SupportedLanguage = SupportedLanguage.ENGLISH,
)

class FeelingsWheelViewModel(
    application: Application,
) : AndroidViewModel(application) {
    private val settingsRepository = SettingsRepository(application)

    private val _uiState = MutableStateFlow(WheelUiState())
    val uiState: StateFlow<WheelUiState> = _uiState.asStateFlow()

    /** Maps outer segment ID -> parent middle segment for O(1) breadcrumb lookup. */
    private lateinit var outerToMiddleMap: Map<String, EmotionSegment>

    private var currentHierarchy: EmotionHierarchy = HierarchyProvider.hierarchyFor(SupportedLanguage.ENGLISH)

    init {
        viewModelScope.launch {
            combine(
                settingsRepository.selectedPalette,
                settingsRepository.selectedLanguage,
            ) { palette, language ->
                palette to language
            }.collect { (palette, language) ->
                rebuildSegments(palette, language)
            }
        }
    }

    private fun rebuildSegments(
        palette: WheelPalette,
        language: SupportedLanguage,
    ) {
        currentHierarchy = HierarchyProvider.hierarchyFor(language)
        val segments = EmotionData.buildSegments(currentHierarchy, palette)
        val middleSegments = segments.filter { it.layer == WheelLayer.MIDDLE }
        outerToMiddleMap =
            segments
                .filter { it.layer == WheelLayer.OUTER }
                .associateBy(
                    keySelector = { it.id },
                    valueTransform = { outer ->
                        middleSegments.first { mid ->
                            mid.coreEmotion == outer.coreEmotion &&
                                outer.startAngle >= mid.startAngle &&
                                outer.startAngle < mid.startAngle + mid.sweepAngle
                        }
                    },
                )
        _uiState.update {
            it.copy(
                segments = segments,
                currentPalette = palette,
                currentLanguage = language,
                selectedEmotion = null,
            )
        }
    }

    fun selectSegment(segment: EmotionSegment) {
        val coreLabel = currentHierarchy.coreLabels[segment.coreEmotion] ?: segment.coreEmotion.name
        val selected =
            when (segment.layer) {
                WheelLayer.CORE ->
                    SelectedEmotion(
                        segment = segment,
                        coreName = segment.label,
                    )
                WheelLayer.MIDDLE ->
                    SelectedEmotion(
                        segment = segment,
                        coreName = coreLabel,
                        middleName = segment.label,
                    )
                WheelLayer.OUTER -> {
                    val middleSegment = outerToMiddleMap[segment.id]
                    SelectedEmotion(
                        segment = segment,
                        coreName = coreLabel,
                        middleName = middleSegment?.label,
                        outerName = segment.label,
                    )
                }
            }

        _uiState.update { it.copy(selectedEmotion = selected) }
    }

    fun clearSelection() {
        _uiState.update { it.copy(selectedEmotion = null) }
    }

    fun setPalette(palette: WheelPalette) {
        viewModelScope.launch {
            settingsRepository.setPalette(palette)
        }
    }

    fun setLanguage(language: SupportedLanguage) {
        viewModelScope.launch {
            settingsRepository.setLanguage(language)
            val localeList = LocaleListCompat.forLanguageTags(language.tag)
            AppCompatDelegate.setApplicationLocales(localeList)
        }
    }
}
