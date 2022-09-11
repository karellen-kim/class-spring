package com.example.classspring

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.Disposable

@RestController
@RequestMapping("/reactive-kafka")
class ReactiveKafkaController(
    private val kafkaTemplate: ReactiveKafkaProducerTemplate<String, String>,
    @Value(value = "\${kafka.reactive.default-topic:reactive-test}") private val topic: String) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/produce")
    fun produce(@RequestParam message: String): Disposable {
        return kafkaTemplate.send(topic, message)
            .doOnSuccess {
                logger.info("Send kafka reactive message: ${message}")
            }
            .subscribe()
    }
}