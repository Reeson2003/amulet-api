package ru.reeson2003.amulet.state

data class Transition(val id: String, val name: String) {
    companion object {
        operator fun invoke(action: Action): Transition? {
            return if ("go" in action.form.keys)
                Transition(action.form["go"] ?: "", action.name)
            else null
        }
    }
}

fun getTransitions(actions: List<Action>): List<Transition> = actions.mapNotNull { Transition(it) }
