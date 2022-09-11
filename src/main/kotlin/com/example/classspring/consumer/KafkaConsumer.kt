package com.example.classspring.consumer

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaConsumer {
    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(id = "my-group", topics = ["test"])
    fun listen(message: String?) {
        logger.info("Consume message: ${message}")
    }
}