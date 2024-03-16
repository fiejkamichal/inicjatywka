package org.mechanika.inicjatywka.core.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import net.harawata.appdirs.AppDirs
import net.harawata.appdirs.AppDirsFactory
import org.mechanika.inicjatywka.database.InicjatywkaDatabase
import java.io.File


actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        val appDirs: AppDirs = AppDirsFactory.getInstance()
        val appVersion = System.getProperty("jpackage.app-version")
        val appName = "org.mechanika.inicjatywka"

        println("appVersion: $appVersion")
        println("appName: $appName")
        val userDir = File(appDirs.getUserDataDir(appName, appVersion, appName))

        if (!userDir.exists()) {
            userDir.mkdirs()
        }

        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:${userDir}/database.db")
        InicjatywkaDatabase.Schema.create(driver)
        return driver

    }
}