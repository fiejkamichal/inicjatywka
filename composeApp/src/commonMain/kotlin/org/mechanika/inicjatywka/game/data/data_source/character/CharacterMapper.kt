package org.mechanika.inicjatywka.game.data.data_source.character

import org.mechanika.inicjatywka.characterdatabase.CharacterCardEntity
import org.mechanika.inicjatywka.game.domain.model.character.CharacterCard

fun CharacterCardEntity.toCharacterCard(): CharacterCard {
    return CharacterCard(
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