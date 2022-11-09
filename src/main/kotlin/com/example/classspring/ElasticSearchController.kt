package com.example.classspring

import co.elastic.clients.elasticsearch.ElasticsearchClient
import co.elastic.clients.elasticsearch.core.SearchRequest
import com.example.classspring.domain.ESUser
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/es")
class ElasticSearchController(
    private val client: ElasticsearchClient
) {

    // https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/current/building-objects.html
    @GetMapping("/search")
    fun search(@RequestParam q: String): List<ESUser?> {
        // scrollId 확인
        return client
            .search({ s: SearchRequest.Builder ->
                s.index("user").query {
                    it.term {
                        it.field("name").value(q)
                    }
                }}, ESUser::class.java)
            .hits().hits().map {
                it.source()
            }
    }
}