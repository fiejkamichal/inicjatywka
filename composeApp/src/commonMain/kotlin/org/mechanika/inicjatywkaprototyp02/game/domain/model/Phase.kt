package org.mechanika.inicjatywkaprototyp02.game.domain.model

data class Phase(
    val value: Phases,
    val id: Long? = null
) {
    companion object {
        val phases = listOf(Phases.Initial, Phases.Initiative)
    }
    enum class Phases {
        Initial,
        Initiative
    }
}