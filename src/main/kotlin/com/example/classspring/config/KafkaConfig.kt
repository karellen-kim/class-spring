package com.example.classspring.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
public class KafkaConfig(
    @Value("\${kafka.bootstrap.host:'localhost:9092'}") private val bootstrapAddress: String,
) {
    /*
    @Bean(KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    fun kStreamsConfig(): KafkaStreamsConfiguration {
        val configs = mapOf(
            StreamsConfig.APPLICATION_ID_CONFIG to "streams-app",
            StreamsConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapAddress,
            StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG to Serdes.String().javaClass,
            StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG to Serdes.String().javaClass)

        return KafkaStreamsConfiguration(configs);
    }
     */
}