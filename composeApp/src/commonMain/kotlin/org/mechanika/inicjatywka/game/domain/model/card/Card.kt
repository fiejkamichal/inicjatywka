package org.mechanika.inicjatywka.game.domain.model.card

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Card(
    var id: Long?,
    private var name: String = "",
    var initiative: Long = 50,
    val deleted: Boolean = false,
    private var ally: Boolean = true,
    private var hitPoints: Long = 0,
    private var resilience: Long = 0,
    private var mana: Long = 0,
    private var concentration: Long = 0,
    private var movePoints: Long = 0,
    private var steps: Long = 0,
    private var states: String = "",
    var waits: Boolean = false
) {
    @Transient
    private var stats: Map<Stat.Id, Stat> = emptyMap()

    init {
        stats = mapOf(
            Stat.Id.Name to Stat(Stat.Id.Name, "nazwa", name),
            Stat.Id.Initiative to Stat(Stat.Id.Initiative, "inicjatywa", initiative.toString()),
            Stat.Id.Ally to Stat(Stat.Id.Ally, "sojusznik", ally.toString()),
            Stat.Id.HitPoints to Stat(Stat.Id.HitPoints, "punkty życia", hitPoints.toString()),
            Stat.Id.Resilience to Stat(Stat.Id.Resilience, "wytrzymałość", resilience.toString()),
            Stat.Id.Mana to Stat(Stat.Id.Mana, "mana", mana.toString()),
            Stat.Id.Concentration to Stat(
                Stat.Id.Concentration,
                "koncentracja",
                concentration.toString()
            ),
            Stat.Id.MovePoints to Stat(Stat.Id.MovePoints, "punkty ruchu", movePoints.toString()),
            Stat.Id.Steps to Stat(Stat.Id.Steps, "kroki", steps.toString()),
            Stat.Id.States to Stat(Stat.Id.States, "stany", states),
        )
    }

    fun getStats(): List<Stat> {
        return stats.values.toList()
    }

    fun getStat(id: Stat.Id): Stat {
        return stats[id] ?: Stat(Stat.Id.Null, "", "")
    }

    fun setStat(id: Stat.Id, value: String) {
        stats[id]?.value = value
        when (id) {
            Stat.Id.Name -> name = value
            Stat.Id.Initiative -> initiative = value.toLong()
            Stat.Id.Ally -> ally = value.toBoolean()
            Stat.Id.HitPoints -> hitPoints = value.toLong()
            Stat.Id.Resilience -> resilience = value.toLong()
            Stat.Id.Mana -> mana = value.toLong()
            Stat.Id.Concentration -> concentration = value.toLong()
            Stat.Id.MovePoints -> movePoints = value.toLong()
            Stat.Id.Steps -> steps = value.toLong()
            Stat.Id.States -> states = value
            Stat.Id.Null -> {}
        }
    }

    class Stat(
        val id: Id,
        val name: String,
        var value: String
    ) {
        fun set(v: String) {
            value = v
        }

        enum class Id {
            Null, Name, Initiative, Ally,
            HitPoints, Resilience, Mana, Concentration,
            MovePoints, Steps, States
        }

        override fun toString(): String {
            return "$name ($id): $value"
        }
    }

    override fun toString(): String {
        return id.toString() + " " + getStats().toString() + " waits " + waits.toString()
    }

    fun sameStats(card: Card): Boolean {
        return card.getStats().all {
            stats[it.id]?.value == it.value
        }
    }
}


