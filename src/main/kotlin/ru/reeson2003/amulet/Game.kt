package ru.reeson2003.amulet

import org.jsoup.Jsoup
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.DependsOn
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import ru.reeson2003.amulet.state.State
import javax.annotation.PostConstruct

@DependsOn("login")
@Component
class Game(@Value("\${game.url}") val baseUrl:String, @Autowired val login: Login, @Autowired val moveService: MoveService) {

    val url: String
        get() = "$baseUrl?sid=${login.sid}"

    @Volatile
    lateinit var state: State

    @Scheduled(fixedRateString = "\${game.refresh}")
    @PostConstruct
    fun refresh() {
        val document = Jsoup.connect(url).get()
        state = State(document)
    }

    fun move(locationId: String) {
        moveService.move(locationId, url)
        refresh()
    }
}