package com.example.classspring

import com.example.classspring.domain.User
import com.example.classspring.domain.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/")
class SampleController(
    private val userRepository: UserRepository
) {

    @GetMapping
    fun getSample(): String {
        return "ok"
    }

    @GetMapping("/users/{id}")
    fun getUser(@PathVariable id: Long): Optional<User> {
        return userRepository.findById(id)
    }
}