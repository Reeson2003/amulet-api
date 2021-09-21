package ru.reeson2003.amulet.state

import org.jsoup.nodes.Document
import org.springframework.web.util.UriComponentsBuilder

const val SPEAK = "speak"

data class NPC(val name: String, val id: String)

fun parseNPCs(document: Document): List<NPC> = document.getElementsByTag("a")
    .map { it.text() to it.attr("abs:href") }
    .filter { it.second.contains(SPEAK) }
    .map {
        val (name, link) = it
        val id = UriComponentsBuilder.fromUriString(link).build().queryParams[SPEAK]!![0]
        NPC(name, id)
    }