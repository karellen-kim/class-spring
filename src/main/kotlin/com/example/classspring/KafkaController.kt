package com.example.classspring

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kafka")
class KafkaController(private val kafkaTemplate: KafkaTemplate<String, String>,
    @Value("\${spring.kafka.template.default-topic:test}") private val topic: String) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/produce")
    fun produce(@RequestParam message: String): String {
        logger.info("Send kafka message: ${message}")
        kafkaTemplate.send(topic, message)
        return message
    }
}