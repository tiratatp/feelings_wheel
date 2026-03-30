package com.nuttyknot.feelingswheel.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nuttyknot.feelingswheel.ui.screen.FeelingsWheelScreen
import com.nuttyknot.feelingswheel.ui.screen.SettingsScreen
import com.nuttyknot.feelingswheel.viewmodel.FeelingsWheelViewModel

@Composable
fun AppNavHost(viewModel: FeelingsWheelViewModel = viewModel()) {
    val navController = rememberNavController()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val initialRotation = rememberSaveable { (0..360).random().toFloat() }

    NavHost(navController = navController, startDestination = "wheel") {
        composable("wheel") {
            FeelingsWheelScreen(
                uiState = uiState,
                onSegmentTapped = { viewModel.selectSegment(it) },
                onDismiss = { viewModel.clearSelection() },
                initialRotation = initialRotation,
                onSettingsClick = { navController.navigate("settings") },
            )
        }
        composable("settings") {
            SettingsScreen(
                currentPalette = uiState.currentPalette,
                onPaletteSelected = { viewModel.setPalette(it) },
                currentLanguage = uiState.currentLanguage,
                onLanguageSelected = { viewModel.setLanguage(it) },
                onBackClick = { navController.popBackStack() },
            )
        }
    }
}
