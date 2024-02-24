package org.mechanika.inicjatywka.game.domain.repository

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywka.game.domain.model.engine.Engine

interface EngineRepository {
    fun getPhase(): Flow<Engine.Phases?>
    fun getPhases(): Flow<List<Engine.Phases>>
    fun getCurrentCardIds(): Flow<List<Long?>>
    fun setCurrentCardId(cardId: Long): Long
    fun getCurrentCardId(): Long?
    fun unsetCurrentCardId()
    fun getCurrentCardIdAsFlow(): Flow<Long?>
    fun setPhase(phase: Engine.Phases): Engine.Phases
    fun getRound(): Long
    fun setRound(round: Long)
    fun getRoundAsFlow(): Flow<Long>
}