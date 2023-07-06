package com.example.classspring

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
class User(
    @Id
    val id: Long,
    val group: Long,
    val info: org.bson.Document
)