package com.example.classspring.domain

class ESUser(
    val name: String?,
    val age: Int?
) {
    constructor() : this(null, null) {
    }
}