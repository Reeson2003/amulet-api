package ru.reeson2003.amulet.state

import org.jsoup.nodes.Document
import org.springframework.web.util.UriComponentsBuilder

const val LOOK = "look"

data class PC(val name: String, val link: String)

fun parsePCs(document: Document): List<PC> = document.getElementsByTag("a")
    .asSequence()
    .map { it.text() to it.attr("abs:href") }
    .filter { it.second.contains(LOOK) }
    .map { it.first to UriComponentsBuilder.fromUriString(it.second).build().queryParams[LOOK]!![0] }
    .filter { it.second.startsWith("u") }
    .map {
        val (name, id) = it
        PC(name, id)
    }
    .toList()