package ru.reeson2003.amulet.state

import org.jsoup.nodes.Document

data class Wealth(val remain: Int, val total: Int)

fun parseHP(document: Document): Wealth = parseWealth(document, "progress-bar-danger")

fun parseMP(document: Document): Wealth = parseWealth(document, "progress-bar-waiting")

private fun parseWealth(document: Document, cssClass: String): Wealth {
    val values = document.getElementsByClass(cssClass)[0]
        .text().split(" ")[0]
        .split("/")
        .map { it.toInt() }
    return Wealth(values[0], values[1])
}
