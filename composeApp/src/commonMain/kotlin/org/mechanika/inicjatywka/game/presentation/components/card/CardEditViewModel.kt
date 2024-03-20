package org.mechanika.inicjatywka.game.presentation.components.card

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.mechanika.inicjatywka.game.domain.model.card.Card
import org.mechanika.inicjatywka.game.domain.use_case.card.UpdateCard

class CardEditViewModel(
    private val updateCard: UpdateCard,
) {

    var cardEdit: Card? by mutableStateOf(null)

    fun onEvent(event: CardEditEvent) {
        when (event) {

            is CardEditEvent.EditCard -> {
                cardEdit = event.card
            }

            is CardEditEvent.SaveCard -> {
                event.card.id?.let {
                    updateCard(it, event.card)
                }
            }

            is CardEditEvent.UpdateCardStat -> {
                cardEdit =
                    when (event.id) {
                        Card.Stat.Id.Null -> cardEdit
                        Card.Stat.Id.Name -> cardEdit?.copy(name = event.value)
                        Card.Stat.Id.Initiative -> cardEdit?.copy(initiative = event.value.toLong())
                        Card.Stat.Id.Ally -> cardEdit?.copy(ally = event.value.toBoolean())
                        Card.Stat.Id.HitPoints -> cardEdit?.copy(hitPoints = event.value.toLong())
                        Card.Stat.Id.MaxHitPoints -> cardEdit?.copy(maxHitPoints = event.value.toLong())
                        Card.Stat.Id.Resilience -> cardEdit?.copy(resilience = event.value.toLong())
                        Card.Stat.Id.MaxResilience -> cardEdit?.copy(maxResilience = event.value.toLong())
                        Card.Stat.Id.Mana -> cardEdit?.copy(mana = event.value.toLong())
                        Card.Stat.Id.MaxMana -> cardEdit?.copy(maxMana = event.value.toLong())
                        Card.Stat.Id.Concentration -> cardEdit?.copy(concentration = event.value.toLong())
                        Card.Stat.Id.MaxConcentration -> cardEdit?.copy(maxConcentration = event.value.toLong())
                        Card.Stat.Id.MovePoints -> cardEdit?.copy(movePoints = event.value.toLong())
                        Card.Stat.Id.Steps -> cardEdit?.copy(steps = event.value.toLong())
                        Card.Stat.Id.States -> cardEdit?.copy(states = event.value)
                    }
            }

            CardEditEvent.StopEditCard -> cardEdit = null
        }
    }
}