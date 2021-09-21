package ru.reeson2003.amulet.state

import org.jsoup.nodes.Document

data class Player(val hp: Wealth, val mp: Wealth)

fun parsePlayer(document: Document): Player = Player(parseHP(document), parseMP(document))