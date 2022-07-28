package com.example.classspring

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class SampleController {

    @GetMapping
    fun getSample1(): String {
        return "ok"
    }
}