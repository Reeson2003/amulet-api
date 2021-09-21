package ru.reeson2003.amulet

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestOperations
import javax.annotation.PostConstruct

@Service
class MoveService(@Autowired val restTemplateBuilder: RestTemplateBuilder) {
    lateinit var restOperations: RestOperations

    @PostConstruct
    fun init() = run { restOperations = restTemplateBuilder.build() }

    fun move(locationId: String, url: String) {
        val httpHeaders = HttpHeaders()
        httpHeaders.contentType = MediaType.MULTIPART_FORM_DATA
        val linkedMultiValueMap = LinkedMultiValueMap<String, String>()
        linkedMultiValueMap.add("go", locationId)
        val httpEntity = HttpEntity(linkedMultiValueMap, httpHeaders)
        val result = restOperations.postForEntity(url, httpEntity, String::class.java)
    }
}