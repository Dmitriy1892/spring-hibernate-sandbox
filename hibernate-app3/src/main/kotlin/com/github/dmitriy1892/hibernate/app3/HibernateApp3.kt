package com.github.dmitriy1892.hibernate.app3

import com.github.dmitriy1892.hibernate.app3.model.ActorEntity
import com.github.dmitriy1892.hibernate.app3.model.MovieEntity
import com.github.dmitriy1892.utils.executeInTransaction
import org.hibernate.Hibernate
import org.hibernate.Session
import org.hibernate.cfg.Configuration

fun main() {
    val config = Configuration()
        .addAnnotatedClass(ActorEntity::class.java)
        .addAnnotatedClass(MovieEntity::class.java)

    val sessionFactory = config.buildSessionFactory()

//    sessionFactory.currentSession.addMovieWithActor()

    val movie = sessionFactory.currentSession.getMovieWithActors(2)
    println("Movie: ${movie.name}")
    println("Actors:")
    movie.actors.forEach { println("- ${it.name}, ${it.age}") }


    sessionFactory.close()
}

private fun Session.addMovieWithActor() = executeInTransaction {
    // create
    val movie = MovieEntity(name = "Pulp fiction", yearOfProduction = 1994)
    val actor1 = ActorEntity(name = "Harvey Keytell", age = 81)
    val actor2 = ActorEntity(name = "Samuel L. Jackson", age = 72)

    // set
    movie.actors = setOf(actor1, actor2)
    actor1.movies = setOf(movie)
    actor2.movies = setOf(movie)

    // save
    persist(movie)
}

private fun Session.getMovieWithActors(movieId: Int): MovieEntity = executeInTransaction {
    get(MovieEntity::class.java, movieId)
        .also { Hibernate.initialize(it.actors) }
}