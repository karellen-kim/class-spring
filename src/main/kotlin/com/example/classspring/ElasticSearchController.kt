package com.example.classspring

import co.elastic.clients.elasticsearch.ElasticsearchClient
import co.elastic.clients.elasticsearch._types.mapping.Property
import co.elastic.clients.elasticsearch.core.SearchRequest
import com.example.classspring.domain.ESUser
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/es")
class ElasticSearchController(
    private val client: ElasticsearchClient
) {

    @PostMapping("/index")
    fun index() {
        // https://github.com/elastic/elasticsearch-java/blob/d5b93c9bc84d02dcfd1c534ea2c8f70ce3d35d15/java-client/src/test/java/co/elastic/clients/elasticsearch/end_to_end/RequestTest.java#L379
        val fields = Collections.singletonMap("keyword",
            Property.of { p ->
                p.keyword { k ->
                    k.ignoreAbove(256)
                }
            })
        val text = Property.of { p ->
            p.text { t ->
                t.fields(
                    Collections.singletonMap("keyword",
                        Property.of { p ->
                            p.keyword { k ->
                                k.ignoreAbove(256)
                            }
                        })
                )
            }
        }
        val number = Property.of { p ->
            p.integer { t ->
                t.fields(fields)
            }
        }

        client
            .indices()
            .create { builder ->
                builder
                    .index("user")
                    .mappings { mappings ->
                        mappings
                            .properties("id", number)
                            .properties("name", text)
                            .properties("age", number)
                    }
            }
    }

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