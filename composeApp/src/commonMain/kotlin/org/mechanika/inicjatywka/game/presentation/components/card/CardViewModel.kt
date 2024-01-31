package org.mechanika.inicjatywka.game.presentation.components.card

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.mechanika.inicjatywka.game.domain.model.card.Card
import org.mechanika.inicjatywka.game.domain.use_case.card.AddCard
import org.mechanika.inicjatywka.game.domain.use_case.card.DeleteCard
import org.mechanika.inicjatywka.game.domain.use_case.card.GetCards
import org.mechanika.inicjatywka.game.domain.use_case.card.UpdateCard

class CardViewModel(
    private val addCard: AddCard,
    private val deleteCard: DeleteCard,
    private val updateCard: UpdateCard,
    getCards: GetCards
) {

    val state = CardState(
        cards = getCards()
    )

    var cardEdit: Card? by mutableStateOf(null)


    fun onEvent(event: CardEvent) {
        when (event) {
            is CardEvent.DeleteCard -> deleteCard(event.id)
            CardEvent.NewCard -> {
                val newCard = Card(
                    name = "",
                    initiative = 50,
                    id = null
                )
                addCard(newCard)
            }

            is CardEvent.EditCard -> {
                cardEdit = event.card
            }

            is CardEvent.SaveCard -> {
                event.card.id?.let {
                    updateCard(it, event.card)
                }
                cardEdit = null
            }

            is CardEvent.UpdateCardStat -> {
                cardEdit =
                    when (event.id) {
                        Card.Stat.Id.Null -> cardEdit
                        Card.Stat.Id.Name -> cardEdit?.copy(name = event.value)
                        Card.Stat.Id.Initiative -> cardEdit?.copy(initiative = event.value.toLong())
                        Card.Stat.Id.Ally -> cardEdit?.copy(ally = event.value.toBoolean())
                        Card.Stat.Id.HitPoints -> cardEdit?.copy(hitPoints = event.value.toLong())
                        Card.Stat.Id.Resilience -> cardEdit?.copy(resilience = event.value.toLong())
                        Card.Stat.Id.Mana -> cardEdit?.copy(mana = event.value.toLong())
                        Card.Stat.Id.Concentration -> cardEdit?.copy(concentration = event.value.toLong())
                        Card.Stat.Id.MovePoints -> cardEdit?.copy(movePoints = event.value.toLong())
                        Card.Stat.Id.Steps -> cardEdit?.copy(steps = event.value.toLong())
                        Card.Stat.Id.States -> cardEdit?.copy(states = event.value)
                        Card.Stat.Id.Waits -> cardEdit?.copy(waits = event.value.toBoolean())
                    }
            }
        }
    }
}