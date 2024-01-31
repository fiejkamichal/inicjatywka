package org.mechanika.inicjatywka.game.data.data_source.card

import org.mechanika.inicjatywka.carddatabase.CardEntity
import org.mechanika.inicjatywka.game.domain.model.card.Card

fun CardEntity.toCard(): Card {
    return Card(
        id = this.id,
        name = this.name,
        initiative = this.initiative,
        deleted = this.deleted != 0L,
        ally = this.ally != 0L,
        hitPoints = this.hitPoints,
        resilience = this.resilience,
        mana = this.mana,
        concentration = this.concentration,
        movePoints = this.movePoints,
        steps = this.steps,
        states = this.states,
        waits = this.waits != 0L
    )
}