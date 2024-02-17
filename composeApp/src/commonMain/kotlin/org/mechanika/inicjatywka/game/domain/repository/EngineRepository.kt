package org.mechanika.inicjatywka.game.domain.repository

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywka.game.domain.model.engine.Phase

interface EngineRepository {
    fun getPhase(): Flow<Phase?>
    fun setPhase(phase: Phase): Phase
    fun getPhases(): Flow<List<Phase>>
    fun getCurrentCardIds(): Flow<List<Long>>
    fun setCurrentCardId(cardId: Long): Long
    fun getCurrentCardId(): Long?
    fun unsetCurrentCardId()
    fun getCurrentCardIdAsFlow(): Flow<Long?>
}