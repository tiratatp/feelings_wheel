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

Test file: `app/src/test/.../ui/FeelingsWheelScreenshotTest.kt`

## Architecture

### Data Layer (`data/`)
- **`model/`** — Domain types: `CoreEmotion` (enum with 7 emotions + colors per layer), `MiddleEmotion`, `OuterEmotion`, `WheelLayer` (enum defining radius fractions for CORE/MIDDLE/OUTER rings), `EmotionSegment` (computed segment with angle + color), `SelectedEmotion` (breadcrumb of core > middle > outer).
- **`EmotionData`** — Hardcoded emotion hierarchy. `buildSegments()` computes all `EmotionSegment`s by dividing 360° equally among cores, then subdividing for middle/outer layers.

### ViewModel (`viewmodel/`)
- **`FeelingsWheelViewModel`** — Holds `WheelUiState` (segments + selected emotion) via `StateFlow`. `selectSegment()` builds a `SelectedEmotion` with breadcrumb path; `clearSelection()` resets it.

### UI Layer (`ui/`)
- **`screen/FeelingsWheelScreen`** — Root composable. Overlays `FeelingsWheel` with a `SelectionPanel` at the bottom.
- **`components/FeelingsWheel`** — Canvas-based wheel with touch handling. Supports drag-to-rotate with fling physics (`exponentialDecay`) and tap-to-select via hit testing. The wheel center is at bottom-center of the canvas (semi-circle layout).
- **`components/WheelRenderer`** — `DrawScope` extension functions for rendering arcs (layer by layer, core first), borders, and radially-oriented text labels.
- **`components/SelectionPanel`** — Animated bottom panel showing breadcrumb path and selected emotion name.
- **`util/AngleUtils`** — Angle normalization, containment checks, touch-to-angle conversion.
- **`util/HitTestUtils`** — Maps touch coordinates to an `EmotionSegment` by computing radius fraction (determines layer) and un-rotated angle (determines segment).

### Key Design Details
- The wheel is drawn as a **semi-circle** (center at bottom of canvas, `centerY = size.height`).
- Segments are rendered by drawing full arcs then cutting out inner circles with white overdraw.
- Colors are defined per `CoreEmotion` with three shades (core/middle/outer). `useDarkText` flag controls label contrast.
- Detekt config relaxes `MagicNumber`, raises `LongMethod` threshold to 150, and ignores `FunctionNaming` on `@Composable`.
