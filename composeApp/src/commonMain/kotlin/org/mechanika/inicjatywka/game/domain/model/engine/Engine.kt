package org.mechanika.inicjatywka.game.domain.model.engine

data class Engine(
    val phase: Phases,
    val round: Long,
    val reverse: Boolean,
    val cardId: Long?
) {
    companion object {
        val phases = listOf(Phases.Initial, Phases.Initiative)
    }

    enum class Phases {
        Initial,
        Initiative
    }
}