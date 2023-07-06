package com.example.classspring

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<User, Long> {
    fun findByGroup(groupId: Long): List<User>
}