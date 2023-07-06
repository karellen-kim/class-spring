package com.example.classspring

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "books")
class Book(
    @Id
    val id: Long,
    @Field("user_id")
    val userId: Long,
    val info: org.bson.Document
)