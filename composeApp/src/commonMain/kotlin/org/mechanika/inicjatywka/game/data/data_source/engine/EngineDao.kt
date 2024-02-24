package org.mechanika.inicjatywka.game.data.data_source.engine

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywka.game.domain.model.engine.Engine

interface EngineDao {

    fun getPhase(): Flow<Engine.Phases?>
    fun getPhases(): Flow<List<Engine.Phases>>
    fun setCurrentCardId(cardId: Long)
    fun getCurrentCardIds(): Flow<List<Long?>>
    fun getCurrentCardId(): Long?
    fun deleteCurrentCardId()
    fun getCurrentCardIdAsFlow(): Flow<Long?>
    fun setPhase(phase: Engine.Phases)
    fun getEngine(): Engine?
    fun setEngine(phase: Engine.Phases)
    fun getRound(): Long?
    fun setRound(round: Long?)
    fun getRoundAsFlow(): Flow<Long?>
}