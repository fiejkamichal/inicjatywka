package org.mechanika.inicjatywkaprototyp02.game.domain.model.phase

data class Phase(
    val value: Phases
) {
    companion object {
        val phases = listOf(Phases.Initial, Phases.Initiative)
    }
    enum class Phases {
        Initial,
        Initiative
    }
}