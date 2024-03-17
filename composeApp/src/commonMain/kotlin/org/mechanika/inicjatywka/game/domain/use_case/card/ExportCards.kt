package org.mechanika.inicjatywka.game.domain.use_case.card

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.mechanika.inicjatywka.game.domain.repository.CardRepository
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ExportCards(
    private val repository: CardRepository
) {
    operator fun invoke(path: String) {
        val formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
        val current = LocalDateTime.now().format(formatter)

        val file = File(path + File.separator + "KartyPostaci${current}.json")
        file.writeText(Json.encodeToString(repository.getCards()))
    }
}