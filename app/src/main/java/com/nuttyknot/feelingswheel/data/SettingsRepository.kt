package com.nuttyknot.feelingswheel.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.nuttyknot.feelingswheel.data.model.WheelPalette
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsRepository(
    private val context: Context,
) {
    private val paletteKey = stringPreferencesKey("palette_name")

    val selectedPalette: Flow<WheelPalette> =
        context.dataStore.data.map { prefs ->
            when (prefs[paletteKey]) {
                "Classic" -> WheelPalette.Classic
                else -> WheelPalette.Pastel
            }
        }

    suspend fun setPalette(palette: WheelPalette) {
        context.dataStore.edit { prefs ->
            prefs[paletteKey] = palette.name
        }
    }
}
