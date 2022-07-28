package com.example.classspring.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
class User(
    @Id
    @Column(name = "id")
    val id: Long,
    @Column(name = "name")
    val name: String
)