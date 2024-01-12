package org.mechanika.inicjatywkaprototyp02.di

import org.mechanika.inicjatywkaprototyp02.game.data.repository.PhaseRepositoryImpl
import org.mechanika.inicjatywkaprototyp02.game.domain.repository.PhaseRepository
import org.mechanika.inicjatywkaprototyp02.game.domain.use_case.GetPhase
import org.mechanika.inicjatywkaprototyp02.game.domain.use_case.PhaseUseCases
import org.mechanika.inicjatywkaprototyp02.game.domain.use_case.StartInitiative
import org.mechanika.inicjatywkaprototyp02.game.domain.use_case.StopInitiative



class AppModule (
    appModulePlatform: AppModulePlatform
) {
    private val repository:PhaseRepository = PhaseRepositoryImpl(
        dao = appModulePlatform.phaseDao
    )

    val phaseUseCases = PhaseUseCases(
            startInitiative = StartInitiative(repository),
            stopInitiative = StopInitiative(repository),
            getPhase = GetPhase(repository)
        )
}