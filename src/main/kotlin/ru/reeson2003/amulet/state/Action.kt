package ru.reeson2003.amulet.state

import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

data class Action(val name: String, val form: Map<String, String>)

fun parseActions(document: Document): List<Action> = document.getElementsByTag("form")
    .map(::parseAction)

fun parseAction(form: Element): Action {
    val submit = form.getElementsByAttributeValue("type", "submit")[0]
    val name = submit.attr("value")
    val formData = form.getElementsByTag("input")
        .filter { !it.equals(submit) }
        .fold(mapOf()) { acc: Map<String, String>, element ->
            val pair = element.attr("name") to element.attr("value")
            acc.plus(pair)
        }
    return Action(name, formData)
}