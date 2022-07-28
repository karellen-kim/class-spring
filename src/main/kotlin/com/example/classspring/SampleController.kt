package com.example.classspring

import com.example.classspring.domain.User
import com.example.classspring.domain.UserRepository
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import java.util.*

@RestController
@RequestMapping("/")
class SampleController(
    private val userRepository: UserRepository,
    private val webClient: WebClient
) {

    @GetMapping
    fun getSample(): String {
        return "ok"
    }

    @GetMapping("/users/{id}")
    fun getUser(@PathVariable id: Long): Optional<User> {
        return userRepository.findById(id)
    }

    @GetMapping("/call")
    fun call(): String? {
        return webClient
            .method(HttpMethod.GET)
            .uri("http://localhost:8080/users/1")
            .retrieve()
            .bodyToMono(User::class.java)
            .map { it.name }
            .block()
    }
}