# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Feelings Wheel is a single-activity Android app (Jetpack Compose, Kotlin) that renders an interactive emotion wheel. Users spin the wheel and tap segments to identify emotions across three concentric layers: core (7 emotions), middle (~41), and outer (~82). Package: `com.nuttyknot.feelingswheel`, minSdk 26, targetSdk 35.

## Build & Run

```bash
./gradlew assembleDebug          # Build debug APK
./gradlew installDebug           # Build + install (also runs ktlintCheck, lintDebug, detekt)
```

## Linting & Formatting

```bash
./gradlew ktlintFormat           # Auto-format Kotlin files
./gradlew ktlintCheck            # Check formatting
./gradlew detekt                 # Static analysis (config: detekt.yml)
```

A pre-commit hook (`scripts/pre-commit`) runs `ktlintFormat` then `detekt` automatically. It is symlinked into `.git/hooks/` by the `installGitHook` Gradle task (runs automatically on first build).

## Testing

Screenshot tests use [Paparazzi](https://github.com/cashapp/paparazzi) (no device/emulator needed):

```bash
./gradlew testDebugUnitTest                  # Run all unit tests
./gradlew recordPaparazziDebug               # Record golden screenshots
./gradlew verifyPaparazziDebug               # Verify against golden screenshots
```

Test files are under `app/src/test/.../`: screenshot tests (`FeelingsWheelScreenshotTest`), utility tests (`AngleUtilsTest`, `HitTestUtilsTest`), ViewModel tests (`FeelingsWheelViewModelTest`), and data tests (`EmotionDataTest`).

## Architecture

### Data Layer (`data/`)
- **`model/`** — Domain types: `CoreEmotion` (enum with 7 emotions), `EmotionColors` (per-emotion 3-layer colors + `useDarkText` flag), `WheelPalette` (maps `CoreEmotion` → `EmotionColors`; defines `Classic` & `Pastel` palettes), `EmotionHierarchy` (localized emotion labels/descriptions via `MiddleEmotionDef`/`OuterEmotionDef`), `SupportedLanguage` (enum: English, Thai), `WheelLayer` (enum defining radius fractions for CORE/MIDDLE/OUTER rings), `EmotionSegment` (computed segment with angle + color), `SelectedEmotion` (breadcrumb of core > middle > outer).
- **`hierarchy/`** — `HierarchyProvider` (returns `EmotionHierarchy` by `SupportedLanguage`), `EnglishHierarchy`, `ThaiHierarchy` (full emotion vocabularies).
- **`EmotionData`** — `buildSegments()` computes all `EmotionSegment`s from a given `EmotionHierarchy` and `WheelPalette` by dividing 360° equally among cores, then subdividing for middle/outer layers.
- **`SettingsRepository`** — DataStore-backed persistence for palette, language, and onboarding state.

### ViewModel (`viewmodel/`)
- **`FeelingsWheelViewModel`** — Holds `WheelUiState` (segments, selected emotion, current palette, language, onboarding flag) via `StateFlow`. Observes `SettingsRepository` and rebuilds segments when palette or language changes. Manages segment selection, onboarding dismissal, and settings updates.

### UI Layer (`ui/`)
- **`navigation/AppNavHost`** — Navigation graph with "wheel" and "settings" routes; shares a single `FeelingsWheelViewModel`.
- **`screen/FeelingsWheelScreen`** — Root composable. Overlays `FeelingsWheel` with a `SelectionPanel` at the bottom.
- **`screen/SettingsScreen`** — Settings UI for palette and language selection.
- **`components/FeelingsWheel`** — Canvas-based wheel with touch handling. Supports drag-to-rotate with fling physics (`exponentialDecay`) and tap-to-select via hit testing. The wheel center is at bottom-center of the canvas (semi-circle layout).
- **`components/WheelRenderer`** — `DrawScope` extension functions for rendering arcs (layer by layer, core first), borders, and radially-oriented text labels.
- **`components/SelectionPanel`** — Animated bottom panel showing breadcrumb path and selected emotion name.
- **`components/AppFooter`** — Credits, bug report link, and share button.
- **`util/AngleUtils`** — Angle normalization, containment checks, touch-to-angle conversion.
- **`util/HitTestUtils`** — Maps touch coordinates to an `EmotionSegment` by computing radius fraction (determines layer) and un-rotated angle (determines segment).
- **`util/ColorUtils`** — `Color.darken()` extension.

### Testing
- `FeelingsWheelScreenshotTest` — Paparazzi screenshot tests for the wheel.
- `AngleUtilsTest`, `HitTestUtilsTest` — Unit tests for geometry utilities.
- `FeelingsWheelViewModelTest` — ViewModel logic tests.
- `EmotionDataTest` — Segment computation tests.

### Key Design Details
- The wheel is drawn as a **semi-circle** (center at bottom of canvas, `centerY = size.height`).
- Segments are rendered by drawing full arcs then cutting out inner circles with white overdraw.
- Colors are decoupled from `CoreEmotion` via `WheelPalette`/`EmotionColors`, enabling palette switching (Classic/Pastel).
- Emotion labels are decoupled via `EmotionHierarchy`, enabling multi-language support.
- Detekt config relaxes `MagicNumber`, raises `LongMethod` threshold to 150, and ignores `FunctionNaming` on `@Composable`.
