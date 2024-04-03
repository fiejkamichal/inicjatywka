package org.mechanika.inicjatywka.game.domain.use_case.card

import kotlinx.serialization.json.Json
import org.mechanika.inicjatywka.game.domain.model.action.CardAddAction
import org.mechanika.inicjatywka.game.domain.model.card.Card
import org.mechanika.inicjatywka.game.domain.repository.CardRepository
import org.mechanika.inicjatywka.game.domain.use_case.action.Stack
import kotlin.io.path.Path
import kotlin.io.path.readText

class ImportCards(
    private val repository: CardRepository,
    private val stack: Stack
) {
    operator fun invoke(path: String) {
        println("Importing cards from $path")
        val cardsInDb = repository.getCards()
        Json.decodeFromString<List<Card?>>(Path(path).readText())
            .onEach { card ->
                if (card != null) {
                    if (cardsInDb.none { it -> it.getStat(Card.Stat.Id.Name).value == card.getStat(Card.Stat.Id.Name).value }) {
                        val id = repository.insertCard(card)
                        card.id = id
                        stack.pushActionAboveCurrentPosition(
                            CardAddAction(
                                cardId = id
                            )
                        )
                    }
                }
            }
    }
}