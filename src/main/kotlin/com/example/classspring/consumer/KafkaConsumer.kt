package com.example.classspring.consumer

import com.example.classspring.domain.CustomMessage
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaConsumer {
    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(id = "my-group", topics = ["test"])
    fun listen(message: CustomMessage?) {
        logger.info("Consume message: name=${message?.name}, value=${message?.value}")
    }
}