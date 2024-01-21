package org.mechanika.inicjatywka.game.presentation.components.character

import org.mechanika.inicjatywka.game.domain.model.character.CharacterCard
import org.mechanika.inicjatywka.game.domain.use_case.character.AddCharacterCard
import org.mechanika.inicjatywka.game.domain.use_case.character.DeleteCharacterCard
import org.mechanika.inicjatywka.game.domain.use_case.character.GetCharacterCards

class CharacterViewModel(
    private val addCharacterCard: AddCharacterCard,
    private val deleteCharacterCard: DeleteCharacterCard,
    getCharacterCards: GetCharacterCards
) {

    val state = CharacterState(
        cards = getCharacterCards()
    )

    fun onEvent(event: CharacterEvent) {
        when (event) {
            is CharacterEvent.DeleteCharacter -> deleteCharacterCard(event.id)
            CharacterEvent.NewCharacter -> {
                val newCard = CharacterCard(
                    name = "",
                    initiative = 50,
                    id = null
                )
                addCharacterCard(newCard)
            }
        }
    }
}