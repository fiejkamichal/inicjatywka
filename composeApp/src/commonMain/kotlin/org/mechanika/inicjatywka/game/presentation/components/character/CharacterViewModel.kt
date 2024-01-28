package org.mechanika.inicjatywka.game.presentation.components.character

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.mechanika.inicjatywka.game.domain.model.character.CharacterCard
import org.mechanika.inicjatywka.game.domain.use_case.character.AddCharacterCard
import org.mechanika.inicjatywka.game.domain.use_case.character.DeleteCharacterCard
import org.mechanika.inicjatywka.game.domain.use_case.character.GetCharacterCards
import org.mechanika.inicjatywka.game.domain.use_case.character.UpdateCharacterCard

class CharacterViewModel(
    private val addCharacterCard: AddCharacterCard,
    private val deleteCharacterCard: DeleteCharacterCard,
    private val updateCharacterCard: UpdateCharacterCard,
    getCharacterCards: GetCharacterCards
) {

    val state = CharacterState(
        cards = getCharacterCards()
    )

    var characterCardEdit: CharacterCard? by mutableStateOf(null)


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

            is CharacterEvent.EditCharacter -> {
                characterCardEdit = event.character
            }

            is CharacterEvent.SaveCharacter -> {
                event.character.id?.let {
                    updateCharacterCard(it, event.character)
                }
                characterCardEdit = null
            }

            is CharacterEvent.UpdateCharacterStat -> {
                characterCardEdit =
                    when (event.id) {
                        CharacterCard.Stat.Id.Null -> characterCardEdit
                        CharacterCard.Stat.Id.Name -> characterCardEdit?.copy(name = event.value)
                        CharacterCard.Stat.Id.Initiative -> characterCardEdit?.copy(initiative = event.value.toLong())
                        CharacterCard.Stat.Id.Ally -> characterCardEdit?.copy(ally = event.value.toBoolean())
                        CharacterCard.Stat.Id.HitPoints -> characterCardEdit?.copy(hitPoints = event.value.toLong())
                        CharacterCard.Stat.Id.Resilience -> characterCardEdit?.copy(resilience = event.value.toLong())
                        CharacterCard.Stat.Id.Mana -> characterCardEdit?.copy(mana = event.value.toLong())
                        CharacterCard.Stat.Id.Concentration -> characterCardEdit?.copy(concentration = event.value.toLong())
                        CharacterCard.Stat.Id.MovePoints -> characterCardEdit?.copy(movePoints = event.value.toLong())
                        CharacterCard.Stat.Id.Steps -> characterCardEdit?.copy(steps = event.value.toLong())
                        CharacterCard.Stat.Id.States -> characterCardEdit?.copy(states = event.value)
                        CharacterCard.Stat.Id.Waits -> characterCardEdit?.copy(waits = event.value.toBoolean())
                    }
            }
        }
    }
}