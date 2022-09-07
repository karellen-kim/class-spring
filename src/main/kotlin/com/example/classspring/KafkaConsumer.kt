package com.example.classspring

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaConsumer {
    private val log = LoggerFactory.getLogger(javaClass)

    @KafkaListener(id = "my-group", topics = ["test"])
    fun listen(message: String?) {
        log.info("Consume message: ${message}")
    }
}