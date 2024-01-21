package org.mechanika.inicjatywka.game.data.data_source.character

import org.mechanika.inicjatywka.characterdatabase.CharacterCardEntity
import org.mechanika.inicjatywka.game.domain.model.character.CharacterCard

fun CharacterCardEntity.toCharacterCard(): CharacterCard {
    return CharacterCard(
        id = this.id,
        name = this.name,
        initiative = this.initiative
    )
}