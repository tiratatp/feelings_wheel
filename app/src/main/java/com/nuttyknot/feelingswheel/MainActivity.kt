package com.nuttyknot.feelingswheel

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.nuttyknot.feelingswheel.data.SettingsRepository
import com.nuttyknot.feelingswheel.data.model.SupportedLanguage
import com.nuttyknot.feelingswheel.ui.navigation.AppNavHost
import com.nuttyknot.feelingswheel.ui.theme.FeelingsWheelTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        if (savedInstanceState == null) {
            val language = runBlocking { SettingsRepository(this@MainActivity).selectedLanguage.first() }
            if (language != SupportedLanguage.ENGLISH) {
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(language.tag))
            }
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FeelingsWheelTheme {
                AppNavHost()
            }
        }
    }
}
