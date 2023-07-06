package com.example.classspring

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class IndexController(
    private val mongoService: MongoService
) {

    @GetMapping("/users/{id}")
    fun getUser(@PathVariable id: Long): User? {
        return mongoService.findUserById(id)
    }

    @GetMapping("/groups/{id}/users")
    fun getUserByGroup(@PathVariable id: Long): List<User> {
        return mongoService.findUserByGroupId(id)
    }
}