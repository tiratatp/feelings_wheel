plugins {
    id("com.android.application") version "8.10.1" apply false
    id("org.jetbrains.kotlin.android") version "2.1.21" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.21" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.8"
    id("org.jlleitschuh.gradle.ktlint") version "14.0.1" apply false
    id("com.github.triplet.play") version "3.13.0" apply false
}

tasks.register("installGitHook") {
    val hookFile = file(".git/hooks/pre-commit")
    val scriptFile = file("scripts/pre-commit")
    outputs.file(hookFile)
    inputs.file(scriptFile)
    doLast {
        if (hookFile.exists()) hookFile.delete()
        val relative = hookFile.parentFile.toPath().relativize(scriptFile.toPath())
        Runtime.getRuntime().exec(arrayOf("ln", "-s", relative.toString(), hookFile.absolutePath)).waitFor()
    }
}

// Run installGitHook automatically on first build
tasks.matching { it.name == "prepareKotlinBuildScriptModel" }.configureEach {
    dependsOn("installGitHook")
}

tasks.register<Copy>("copyScreenshots") {
    dependsOn(":app:testDebugUnitTest")
    description = "Copy Paparazzi snapshots to repo root for README"
    group = "documentation"
    val snapshotDir = "app/src/test/snapshots/images"
    val prefix = "com.nuttyknot.feelingswheel.ui_FeelingsWheelScreenshotTest_"
    from(snapshotDir) {
        include("${prefix}defaultState.png")
        rename("${prefix}defaultState.png", "screenshot-wheel-default.png")
    }
    from(snapshotDir) {
        include("${prefix}selectedOuterEmotion.png")
        rename("${prefix}selectedOuterEmotion.png", "screenshot-wheel-selected.png")
    }
    val tablet10Prefix = "com.nuttyknot.feelingswheel.ui_FeelingsWheelTablet10ScreenshotTest_"
    from(snapshotDir) {
        include("${tablet10Prefix}defaultState.png")
        rename("${tablet10Prefix}defaultState.png", "screenshot-tablet-default.png")
    }
    into("$rootDir/assets")
}

subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    detekt {
        buildUponDefaultConfig = true
        allRules = false
        config.setFrom(files("${rootProject.projectDir}/detekt.yml"))
    }
}
