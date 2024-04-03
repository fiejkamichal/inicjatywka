package org.mechanika.inicjatywka.game.data.data_source.card

import org.mechanika.inicjatywka.database.CardEntity
import org.mechanika.inicjatywka.game.domain.model.card.Card

fun CardEntity.toCard(): Card {
    return Card(
        id = this.id,
        name = this.name,
        initiative = this.initiative,
        deleted = this.deleted != 0L,
        ally = this.ally != 0L,
        hitPoints = this.hitPoints,
        maxHitPoints = this.maxHitPoints,
        resilience = this.resilience,
        maxResilience = this.maxResilience,
        mana = this.mana,
        maxMana = this.maxMana,
        concentration = this.concentration,
        maxConcentration = this.maxConcentration,
        movePoints = this.movePoints,
        steps = this.steps,
        states = this.states,
        waits = this.waits != 0L
    )
}