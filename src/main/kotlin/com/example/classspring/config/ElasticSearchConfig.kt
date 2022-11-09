package com.example.classspring.config

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient
import co.elastic.clients.elasticsearch.ElasticsearchClient
import co.elastic.clients.json.jackson.JacksonJsonpMapper
import co.elastic.clients.transport.rest_client.RestClientTransport
import org.apache.http.HttpHost
import org.elasticsearch.client.RestClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class ElasticSearchConfig {

    // https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/current/introduction.html
    @Bean
    fun elasticSearchClient(): ElasticsearchClient {
        val restClient = RestClient.builder(
            HttpHost("localhost", 19200)
        ).build()

        val transport = RestClientTransport(
            restClient, JacksonJsonpMapper()
        )

        return ElasticsearchClient(transport)
    }

    @Bean
    fun elasticsearchAsyncClient(): ElasticsearchAsyncClient {
        val restClient = RestClient.builder(
            HttpHost("localhost", 19200)
        ).build()

        val transport = RestClientTransport(
            restClient, JacksonJsonpMapper()
        )

        return ElasticsearchAsyncClient(transport)
    }
}