package com.github.dmitriy1892.hibernate.app2

import com.github.dmitriy1892.hibernate.app2.model.ItemEntity
import com.github.dmitriy1892.hibernate.app2.model.PersonEntity
import com.github.dmitriy1892.utils.executeInTransaction
import org.hibernate.Session
import org.hibernate.cfg.Configuration

fun main() {
    val configuration = Configuration()
        .addAnnotatedClass(PersonEntity::class.java)
        .addAnnotatedClass(ItemEntity::class.java)

    val sessionFactory = configuration.buildSessionFactory()

//    val person = sessionFactory.currentSession.addNewItem()
//    println("==================")
//    println(person)
//    println(person.items)

//    sessionFactory.currentSession.removeLastItem(1)
//    val person = sessionFactory.currentSession.getPerson(1)
//    println("==========")
//    println("After removing last item")
//    println(person)
//    println(person.items)
//    println("==========")

//    sessionFactory.currentSession.deletePerson(1)
//    sessionFactory.currentSession.printAllItems()

    sessionFactory.currentSession.setNewOwnerForAllItems(2)
    sessionFactory.currentSession.printAllItems()
}

private fun Session.addNewItem(): PersonEntity = executeInTransaction {
    val person = get(PersonEntity::class.java, 1)

    val newItem = ItemEntity(itemName = "NewItem", owner = person)

    person.items = person.items
        .toMutableList()
        .apply { add(newItem) }
        .toList()

    persist(newItem)
    person
}

private fun Session.removeLastItem(personId: Int) = executeInTransaction {
    val person = get(PersonEntity::class.java, 1)

    println("==========")
    println("Before removing last item")
    println(person)
    println(person.items)
    println("==========")

    val lastItem = person.items.last()

    remove(lastItem)
}

private fun Session.getPerson(personId: Int): PersonEntity = executeInTransaction {
    get(PersonEntity::class.java, 1)
}

private fun Session.deletePerson(personId: Int) = executeInTransaction {
    val person = get(PersonEntity::class.java, personId)
    remove(person)
    person.items.forEach { item ->
        item.owner = null
    }
}

private fun Session.printAllItems() = executeInTransaction {
    val items = createQuery("FROM ItemEntity", ItemEntity::class.java).resultList
    println(items)
}

private fun Session.setNewOwnerForAllItems(personId: Int) = executeInTransaction {
    val person = get(PersonEntity::class.java, personId)
    val items = createQuery("FROM ItemEntity", ItemEntity::class.java).resultList
    items.forEach { item -> item.owner = person }
}