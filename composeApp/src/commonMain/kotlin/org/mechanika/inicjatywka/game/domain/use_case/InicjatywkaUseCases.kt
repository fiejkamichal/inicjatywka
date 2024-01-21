package org.mechanika.inicjatywka.game.domain.use_case

import org.mechanika.inicjatywka.game.domain.use_case.action.Actions
import org.mechanika.inicjatywka.game.domain.use_case.action.Redo
import org.mechanika.inicjatywka.game.domain.use_case.action.Stack
import org.mechanika.inicjatywka.game.domain.use_case.action.Undo
import org.mechanika.inicjatywka.game.domain.use_case.character.AddCharacterCard
import org.mechanika.inicjatywka.game.domain.use_case.character.DeleteCharacterCard
import org.mechanika.inicjatywka.game.domain.use_case.character.GetCharacterCard
import org.mechanika.inicjatywka.game.domain.use_case.character.GetCharacterCards
import org.mechanika.inicjatywka.game.domain.use_case.debug.Debug
import org.mechanika.inicjatywka.game.domain.use_case.phase.GetPhase
import org.mechanika.inicjatywka.game.domain.use_case.phase.StartInitiative
import org.mechanika.inicjatywka.game.domain.use_case.phase.StopInitiative

data class InicjatywkaUseCases(
    val startInitiative: StartInitiative,
    val stopInitiative: StopInitiative,
    val getPhase: GetPhase,
    val actions: Actions,
    val undoAction: Undo,
    val redoAction: Redo,
    val stack: Stack,
    val debug: Debug,
    val addCharacterCard: AddCharacterCard,
    val deleteCharacterCard: DeleteCharacterCard,
    val getCharacterCard: GetCharacterCard,
    val getCharacterCards: GetCharacterCards
)