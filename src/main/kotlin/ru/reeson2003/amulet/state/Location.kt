package ru.reeson2003.amulet.state

import org.jsoup.nodes.Document

data class Location(val name: String, val description: String)

fun parseLocation(document: Document): Location {
    val name = document.getElementById("header")?.text() ?: "unknown"
    val text = document.getElementsByTag("body")[0].ownText()
    val lastIndexOf = text.lastIndexOf(SEPARATOR)
    val description = text.substring(lastIndexOf + SEPARATOR.length).trim()
    return Location(name, description)
}