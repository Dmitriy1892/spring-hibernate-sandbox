package com.github.dmitriy1892.hibernate.app1.model

import jakarta.persistence.*

@Entity
@Table(name = "person")
data class PersonEntity(
    @Column(name = "name")
    val name: String = "",

    @Column(name = "age")
    val age: Int = 0,

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
)