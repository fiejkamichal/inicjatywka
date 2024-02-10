package org.mechanika.inicjatywka.di

import org.mechanika.inicjatywka.database.InicjatywkaDatabase


expect class AppModulePlatform {
    val db: InicjatywkaDatabase
}