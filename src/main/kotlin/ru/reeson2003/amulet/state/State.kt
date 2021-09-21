package ru.reeson2003.amulet

import org.jsoup.nodes.Document
import ru.reeson2003.amulet.state.*

const val SEPARATOR = "---"

data class State(
    val player: Player,
    val location: Location,
    val actions: List<Action>,
    val transitions: List<Transition>,
    val events: List<Event>,
    val npsc: List<NPC>,
    val pcs: List<PC>,
    val items: List<Item>
) {
    companion object {
        operator fun invoke(document: Document): State {
            val player = parsePlayer(document)
            val location = parseLocation(document)
            val actions = parseActions(document)
            val transitions = getTransitions(actions)
            val events = parseEvents(document)
            val npcs = parseNPCs(document)
            val pcs = parsePCs(document)
            val items = parseItems(document)
            return State(player, location, actions, transitions, events, npcs, pcs, items)
        }
    }
}
