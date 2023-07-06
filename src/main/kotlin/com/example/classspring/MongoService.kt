package com.example.classspring

import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.LookupOperation
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class MongoService(
    private val mongoTemplate: MongoTemplate,
    private val userRepository: UserRepository
) {

    fun findUserById(id: Long): User? {
        return userRepository.findByIdOrNull(id)
    }

    fun findUserByGroupId(id: Long): List<User> {
        return userRepository.findByGroup(id)
    }

    fun getInfos(userId: Long) {
        val infoTables =

            (1 to 4).toList()
                .map { idx ->
                    LookupOperation.newLookup()
                        .from("info_${idx}")
                        .localField("user_id")
                        .foreignField("user_id")
                        .`as`("dataA")
                }

        val lookupA = LookupOperation.newLookup()
            .from("a")
            .localField("user_id")
            .foreignField("user_id")
            .`as`("dataA")
    }

}