package ru.reeson2003.amulet

import org.jsoup.Jsoup
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
@ConfigurationProperties("game")
class Login {

    private val logger = LoggerFactory.getLogger(javaClass)

    lateinit var url: String
    lateinit var login: String
    lateinit var password: String

    lateinit var sid: String

    @PostConstruct
    fun init() {
        val url = "$url?site=connect&login=$login&p=$password"
        sid = Jsoup.connect(url)
            .get()
            .getElementsByAttributeValue("name", "sid")[0]
            .attr("value");
        logger.debug("Got session id: [$sid]")
    }
}