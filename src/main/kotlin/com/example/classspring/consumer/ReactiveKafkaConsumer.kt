package com.example.classspring.consumer

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import java.time.Duration

@Component
class ReactiveKafkaConsumer(
    private val reactiveKafkaConsumerTemplate: ReactiveKafkaConsumerTemplate<String, String>): CommandLineRunner {

    private val logger = LoggerFactory.getLogger(javaClass)

    private fun listen(): Flux<MutableList<ConsumerRecord<String, String>>> {
        return reactiveKafkaConsumerTemplate
            .receiveAutoAck()
            .bufferTimeout(10, Duration.ofSeconds(10))

    }

    override fun run(vararg args: String) {
        listen().subscribe {
            logger.info("Reactive Consume message: ${it.map { it.value() }.joinToString(separator = ", ")}")
        }
    }
}