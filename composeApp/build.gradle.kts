import org.jetbrains.compose.desktop.application.dsl.TargetFormat

val inicjatywkaVersion = "1.9.0"

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.sqlDelight)
    alias(libs.plugins.kotlinSerialization)
    id("dev.icerock.mobile.multiplatform-resources")
}

kotlin {

    sqldelight {
        databases {
            create("InicjatywkaDatabase") {
                packageName = "org.mechanika.inicjatywka.database"
                srcDirs("src/commonMain/sqldelight/database")
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
            implementation(libs.sqldelight.coroutines)
            implementation(libs.sqldelight.async)
            implementation(libs.decompose)
            implementation(libs.decompose.jetbrains)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.mpfilepicker)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.sqldelight.jvm)
            implementation(libs.decompose)
            implementation(libs.appdirs)
        }

        getByName("androidMain").dependsOn(commonMain.get())
        getByName("desktopMain").dependsOn(commonMain.get())
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
        versionCode = 190
        versionName = inicjatywkaVersion
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
            packageVersion = inicjatywkaVersion
            modules("java.sql")
            modules("jdk.unsupported")
            windows {
                dirChooser = true
                perUserInstall = true
                upgradeUuid = "BD51EE71-2707-4BF3-BFEB-20C3BA1B8EC5"
                menuGroup = "Inicjatywka"
            }
        }
    }
}

dependencies {
    commonMainApi(libs.moko.core)
    commonMainApi(libs.moko.compose)
    commonMainApi(libs.resources)
    commonMainApi(libs.resources.compose)
}

multiplatformResources {
    multiplatformResourcesPackage = "org.mechanika.inicjatywka"
}