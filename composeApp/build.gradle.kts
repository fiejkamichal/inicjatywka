import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.sqlDelight)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {

    sqldelight {
        databases {
            create("PhaseDatabase") {
                packageName = "org.mechanika.inicjatywka.phasedatabase"
                srcDirs("src/commonMain/sqldelight/phasedatabase")
            }
            create("ActionDatabase") {
                packageName = "org.mechanika.inicjatywka.actiondatabase"
                srcDirs("src/commonMain/sqldelight/actiondatabase")
            }
            create("CharacterDatabase") {
                packageName = "org.mechanika.inicjatywka.characterdatabase"
                srcDirs("src/commonMain/sqldelight/characterdatabase")
            }
        }
    }

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    jvm("desktop")
    
    sourceSets {
        val desktopMain by getting
        
        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.sqldelight.android)
            implementation(libs.decompose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)
            implementation(libs.sqldelight.coroutines)
            implementation(libs.sqldelight.async)
            implementation(libs.decompose)
            implementation(libs.decompose.jetbrains)
            implementation(libs.kotlinx.serialization.json)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.sqldelight.jvm)
            implementation(libs.decompose)
        }
    }
}

android {
    namespace = "org.mechanika.inicjatywka"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "org.mechanika.inicjatywka"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 2
        versionName = "1.2.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"


        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Exe, TargetFormat.Deb)
            packageName = "org.mechanika.inicjatywka"
            packageVersion = "1.2.0"
            modules("java.sql")
        }
    }
}

dependencies {
    commonMainApi(libs.moko.core)
    commonMainApi(libs.moko.compose)
}
