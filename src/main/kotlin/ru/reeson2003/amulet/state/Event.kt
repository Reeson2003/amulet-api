package ru.reeson2003.amulet.state

import org.jsoup.nodes.Document
import ru.reeson2003.amulet.SEPARATOR

data class Event(val description: String)

fun parseEvents(document: Document): List<Event> {
    val body = document.getElementsByTag("body")[0]
    val text = body.ownText()
    val firstIndexOf = text.indexOf(SEPARATOR)
    return text.substring(0, firstIndexOf).split("\n")
        .filter { it.isNotBlank() }
        .map { it.trim() }
        .map(::Event)
}