package ru.reeson2003.amulet

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import org.springframework.web.servlet.view.RedirectView

@RestController
class GameController(val game: Game) {

    @GetMapping("/")
    fun game(): RedirectView = RedirectView(game.url)

    @GetMapping("/game")
    fun getState(): RedirectView =
        RedirectView("${ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()}/game/go/_begin")

    @GetMapping("/game/go/{id}")
    fun move(@PathVariable("id") id: String): Response {
        game.move(id)
        return Response(game.state)
    }
}

data class Response(val state: State, val gos: List<Go> = state.transitions.map { Go(it.name, it.id) })

data class Go(val name: String, val id: String, val link: String = "http://localhost:8080/game/go/${id}")