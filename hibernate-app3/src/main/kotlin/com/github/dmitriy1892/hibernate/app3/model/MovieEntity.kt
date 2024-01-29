package com.github.dmitriy1892.hibernate.app3.model

import jakarta.persistence.*

@Entity
@Table(name = "movie_table")
class MovieEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    var movieId: Int = 0,

    @Column(name = "name", nullable = false, length = 200)
    var name: String = "",

    @Column(name = "year_of_production", columnDefinition = "int check(year_of_production > 1900)")
    var yearOfProduction: Int = 1901,

    @ManyToMany(
        mappedBy = "movies",
        cascade = [CascadeType.PERSIST]
    )
    var actors: Set<ActorEntity> = emptySet()
)