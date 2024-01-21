package org.mechanika.inicjatywka.game.domain.model.character

class CharacterCard(
    var id: Long?,
    val name: String,
    val initiative: Long
) {
    fun getStats(): List<Stat> {
        return listOf(
            Stat("nazwa", this.name),
            Stat("inicjatywa", this.initiative.toString())
        )
    }

    class Stat(
        val name: String,
        val value: String
    ) {
        override fun toString(): String {
            return "$name $value"
        }
    }

    override fun toString(): String {
        return id.toString() + " " + getStats().toString()
    }
}


