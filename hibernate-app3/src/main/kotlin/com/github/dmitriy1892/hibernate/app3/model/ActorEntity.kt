package com.github.dmitriy1892.hibernate.app3.model

import jakarta.persistence.*

@Entity
@Table(name = "actor_table")
class ActorEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    var actorId: Int = 0,

    @Column(
        name = "name",
        nullable = false,
        length = 100,
        unique = true
    )
    var name: String = "",

    @Column(name = "age", nullable = false, columnDefinition = "int not null check(age < 100)")
    var age: Int = 0,

    @ManyToMany(
        targetEntity = MovieEntity::class,
        cascade = [CascadeType.PERSIST]
    )
    @JoinTable(
        name = "actor_movie_table",
        joinColumns = [JoinColumn(name = "actor_id")],
        inverseJoinColumns = [JoinColumn(name = "movie_id")]
    )
    var movies: Set<MovieEntity> = emptySet()
)
