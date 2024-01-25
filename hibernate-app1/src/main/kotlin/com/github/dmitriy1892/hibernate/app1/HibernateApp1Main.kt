package com.github.dmitriy1892.hibernate.app1

import com.github.dmitriy1892.hibernate.app1.model.PersonEntity
import com.github.dmitriy1892.utils.executeInTransaction
import org.hibernate.Session
import org.hibernate.cfg.Configuration

fun main() {
    val configuration = Configuration().addAnnotatedClass(PersonEntity::class.java)

    val sessionFactory = configuration.buildSessionFactory()

    val session = sessionFactory.currentSession

//    session.insertUsers()
//    session.updateUser()
//    session.deleteUser()

//    val id = session.insertUserWithIdReturning()
//    println("Saved with id = $id")

    session.doWithHql()
}

fun Session.insertUsers() = executeInTransaction {
    val person1 = PersonEntity("Test1", 30)
    val person2 = PersonEntity("Test2", 40)
    val person3 = PersonEntity("Test3", 50)

    persist(person1)
    persist(person2)
    persist(person3)
}

fun Session.updateUser() = executeInTransaction {
    val person = get(PersonEntity::class.java, 2)
    val updatedPerson = person.copy(name = "New name")
    merge(updatedPerson)
}

fun Session.deleteUser() = executeInTransaction {
    val person = get(PersonEntity::class.java, 3)
    remove(person)
}

fun Session.insertUserWithIdReturning(): Int = executeInTransaction {
    val person = PersonEntity("Test4", 32)
    persist(person)
    return@executeInTransaction person.id
}

fun Session.doWithHql() = executeInTransaction {
    val people = createQuery("FROM PersonEntity WHERE name LIKE 'Test%'", PersonEntity::class.java)
        .resultList

    people.forEach { println(it) }
}

