package org.mechanika.inicjatywka.game.domain.model.character

class CharacterCard(
    var id: Long?,
    val name: String = "",
    val initiative: Long = 50,
    val deleted: Boolean = false,
    val ally: Boolean = true,
    val hitPoints: Long = 0,
    val resilience: Long = 0,
    val mana: Long = 0,
    val concentration: Long = 0,
    val movePoints: Long = 0,
    val steps: Long = 0,
    val states: String = "",
    val waits: Boolean = false
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


