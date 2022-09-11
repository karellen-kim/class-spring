package com.example.classspring.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate
import reactor.kafka.receiver.ReceiverOptions
import reactor.kafka.sender.SenderOptions
import java.util.*


@Configuration
class ReactiveKafkaConfig {
    @Bean
    fun reactiveKafkaProducerTemplate(
        properties: KafkaProperties
    ): ReactiveKafkaProducerTemplate<String, String>? {
        val props = properties.buildProducerProperties()
        return ReactiveKafkaProducerTemplate(SenderOptions.create(props))
    }

    @Bean
    fun kafkaReceiverOptions(
        @Value(value = "\${kafka.reactive.default-topic:reactive-test}") topic: String,
        kafkaProperties: KafkaProperties
    ): ReceiverOptions<String, String> {
        val basicReceiverOptions = ReceiverOptions.create<String, String>(kafkaProperties.buildConsumerProperties())
        return basicReceiverOptions.subscription(Collections.singletonList(topic))
    }

    @Bean
    fun reactiveKafkaConsumerTemplate(kafkaReceiverOptions: ReceiverOptions<String, String>): ReactiveKafkaConsumerTemplate<String, String> {
        return ReactiveKafkaConsumerTemplate(kafkaReceiverOptions)
    }
}