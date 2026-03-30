package com.nuttyknot.feelingswheel.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.unit.dp
import com.nuttyknot.feelingswheel.R
import com.nuttyknot.feelingswheel.data.model.CoreEmotion
import com.nuttyknot.feelingswheel.data.model.SupportedLanguage
import com.nuttyknot.feelingswheel.data.model.WheelPalette
import com.nuttyknot.feelingswheel.ui.components.AppFooter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    currentPalette: WheelPalette,
    onPaletteSelected: (WheelPalette) -> Unit,
    currentLanguage: SupportedLanguage,
    onLanguageSelected: (SupportedLanguage) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.settings)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.navigate_back),
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState()),
        ) {
            Text(
                text = stringResource(R.string.settings_color_palette),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            )

            PaletteRow(
                palette = WheelPalette.Pastel,
                label = stringResource(R.string.palette_pastel),
                isSelected = currentPalette.name == WheelPalette.Pastel.name,
                onClick = { onPaletteSelected(WheelPalette.Pastel) },
            )
            PaletteRow(
                palette = WheelPalette.Classic,
                label = stringResource(R.string.palette_classic),
                isSelected = currentPalette.name == WheelPalette.Classic.name,
                onClick = { onPaletteSelected(WheelPalette.Classic) },
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.settings_language),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            )

            SupportedLanguage.entries.forEach { language ->
                LanguageRow(
                    language = language,
                    isSelected = currentLanguage == language,
                    onClick = { onLanguageSelected(language) },
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            AppFooter()
        }
    }
}

@Composable
private fun PaletteRow(
    palette: WheelPalette,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.clearAndSetSemantics {},
        ) {
            CoreEmotion.entries.forEach { emotion ->
                Box(
                    modifier =
                        Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(palette.colorsFor(emotion).coreColor),
                )
            }
        }

        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 12.dp).weight(1f),
        )

        RadioButton(
            selected = isSelected,
            onClick = onClick,
        )
    }
}

@Composable
private fun LanguageRow(
    language: SupportedLanguage,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = language.displayName,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f),
        )

        RadioButton(
            selected = isSelected,
            onClick = onClick,
        )
    }
}
