package org.mechanika.inicjatywka.game.presentation.components.card

import org.mechanika.inicjatywka.game.domain.model.card.Card
import org.mechanika.inicjatywka.game.domain.use_case.card.AddCard
import org.mechanika.inicjatywka.game.domain.use_case.card.DeleteCard
import org.mechanika.inicjatywka.game.domain.use_case.card.GetCards

class CardListViewModel(
    private val addCard: AddCard,
    private val deleteCard: DeleteCard,
    getCards: GetCards
) {

    val state = CardListState(
        cards = getCards()
    )

    fun onEvent(event: CardListEvent) {
        when (event) {
            is CardListEvent.DeleteCard -> deleteCard(event.id)
            CardListEvent.NewCard -> {
                val newCard = Card(
                    name = "",
                    initiative = 50,
                    id = null
                )
                addCard(newCard)
            }
        }
    }
}