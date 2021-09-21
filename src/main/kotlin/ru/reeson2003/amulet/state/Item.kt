package ru.reeson2003.amulet.state

import org.jsoup.nodes.Document
import org.springframework.web.util.UriComponentsBuilder

data class Item(val name: String, val id: String)

fun parseItems(document: Document): List<Item> = document.getElementsByTag("a")
    .asSequence()
    .map { it.text() to it.attr("abs:href") }
    .filter { it.second.contains(LOOK) }
    .map { it.first to UriComponentsBuilder.fromUriString(it.second).build().queryParams[LOOK]!![0] }
    .filter { it.second.startsWith("i") }
    .map {
        val (name, id) = it
        Item(name, id)
    }
    .toList()
